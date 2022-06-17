import React, { useEffect, useState } from "react";
import { jsPDF } from "jspdf";
import { Button } from "react-bootstrap";
import { AppointmentService } from "../../services/AppointmentService";

function ReportPDFAppointment(props) {
  const [report, setReport] = useState({});
  const [appointment, setAppointment] = useState();

  const getReport = (id) => {
    AppointmentService.getAppointmentReport(id)
      .then((response) => {
        setReport(response.data);
      })
      .catch((error) => {
        console.log("Error while getting api");
      });
  };
  const doc = new jsPDF();

  const handleDownloadPDF = () => {
    doc.text(
      `                                                             Datum i vreme: ${appointment.date} ${appointment.time}    

    Pacijent: ${appointment.citizen.name}  ${appointment.citizen.lastname}
    jmbg: ${appointment.citizen.identificationNumber}

  
    Izveštaj pregleda:

    ${report["report"]}`,
      10,
      10
    );
    doc.save("Izveštaj.pdf");
  };

  useEffect(() => {
    console.log(appointment);
    setAppointment(props.appointment);
    getReport(props.appointmentId);
  }, [appointment]);

  return (
    <Button variant="secondary" onClick={handleDownloadPDF}>
      Preuzmi izveštaj
    </Button>
  );
}

export default ReportPDFAppointment;
