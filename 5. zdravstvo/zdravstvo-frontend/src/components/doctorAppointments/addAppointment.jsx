import React, { useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import moment from "moment";
import DatePicker from "react-datepicker";
import setHours from "date-fns/setHours";
import setMinutes from "date-fns/setMinutes";
import "react-datepicker/dist/react-datepicker.css";
import { AppointmentService } from "../../services/AppointmentService";
import { es, is, ru } from "date-fns/locale";
import Swal from "sweetalert2";

function AddAppointment() {
  const [selectedDate, SetSelectedDate] = useState();
  const [excludedTimes, SetExcludedTimes] = useState([]);

  const [reservedDates, setReservedDates] = useState([]);

  const handleSelectedDate = (date) => {
    SetSelectedDate(date);
  };

  const getExcludedTimes = (date) => {
    let arrSpecificDates = [];
    for (let i = 0; i < reservedDates.length; i++) {
      if (
        moment(date, moment.ISO_8601).format("YYYY/MM/DD") ===
        moment(reservedDates[i], moment.ISO_8601).format("YYYY/MM/DD")
      ) {
        arrSpecificDates.push(
          moment(reservedDates[i], moment.ISO_8601).toObject()
        );
      }
    }
    if (arrSpecificDates.length == 0) {
      SetExcludedTimes([]);
    } else {
      let arrExcludedTimes = [];
      for (let i = 0; i < arrSpecificDates.length; i++) {
        arrExcludedTimes.push(
          setHours(
            setMinutes(new Date(), arrSpecificDates[i].minutes),
            arrSpecificDates[i].hours
          )
        );
        SetExcludedTimes(arrExcludedTimes);
      }
    }
  };

  const fetchAppointments = () => {
    AppointmentService.getAppointmentsByDoctor().then((res) => {
      const newArray = res.data.map(
        (appointment) => new Date(appointment.date + ", " + appointment.time)
      );
      setReservedDates(newArray);
    });
  };

  const addFreeAppointment = () => {
    const appointmentRequest = {
      date: selectedDate,
    };
    console.log(appointmentRequest);
    if (appointmentRequest.date == undefined) {
      Swal.fire({
        icon: "error",
        title: "Neispravan datum! Odaberite ponovo",
      });
    } else {
      AppointmentService.createFreeApointment(appointmentRequest).then(
        (response) => {
          if (response.status == 201) {
            Swal.fire({
              icon: "success",
              title: "Slobodni termin je kreiran!",
            }).then((result) => {
              window.location.reload();
            });
          }
        }
      );
    }
  };

  useEffect(() => {
    fetchAppointments();
  }, [selectedDate]);
  return (
    <Container
      style={{
        backgroundColor: "white",
        height: "300px",
        width: "35%",
        padding: "20px",
        marginTop: "40px",
      }}
    >
      <h4 style={{ textAlign: "center" }}>Kreiranje novih termina pregleda</h4>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          margin: "10px",
        }}
      >
        <DatePicker
          selected={selectedDate}
          onChange={handleSelectedDate}
          onSelect={getExcludedTimes}
          popperPlacement="top-start"
          dateFormat="dd/MM/yyy"
          minDate={new Date()}
          placeholderText={"Datum"}
        />
        <DatePicker
          selected={selectedDate}
          excludeTimes={excludedTimes}
          onChange={handleSelectedDate}
          onSelect={getExcludedTimes}
          popperPlacement="top-start"
          showTimeSelect
          showTimeSelectOnly
          timeIntervals={30}
          timeFormat="HH:mm"
          locale={es}
          dateFormat="hh:mm aa"
          minDate={new Date()}
          minTime={new Date()}
          maxTime={setHours(setMinutes(new Date(), 0), 18)}
          placeholderText={"Vreme"}
        />
      </div>
      <div
        style={{ display: "flex", justifyContent: "center", margin: "10px" }}
      >
        <Button onClick={addFreeAppointment}>Kreiraj</Button>
      </div>
    </Container>
  );
}

export default AddAppointment;
