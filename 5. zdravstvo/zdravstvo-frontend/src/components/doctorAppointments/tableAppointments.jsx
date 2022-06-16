import React, { useEffect, useState } from "react";
import { Container, Table } from "react-bootstrap";
import MUIDataTable from "mui-datatables";
import { AppointmentService } from "../../services/AppointmentService";
import { AppointmentReportService } from "../../services/AppointmentReportService";
import { Typography } from "@mui/material";
import ModalReport from "../modal";
import Swal from "sweetalert2";
import ReportPDFAppointment from "./reportPDFAppointment";

function TableAppointments() {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    fetchAppointments();
  }, []);

  async function fetchAppointments() {
    try {
      AppointmentService.getAppointmentsByDoctor().then((res) => {
        setAppointments(res.data);
        console.log(appointments);
      });
    } catch (error) {
      console.error(`Error loading appointments !: ${error}`);
    }
  }

  const saveAppointmentReport = (appointmentReport) => {
    console.log(appointmentReport);
    AppointmentReportService.createAppointmentReport(appointmentReport)
      .then((response) => {
        if (response.status == 201) {
          Swal.fire({
            icon: "success",
            title: "Izveštaj je sačuvan",
          }).then((result) => {
            window.location.reload();
          });
        }
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: error.response.data.message,
        });
      });
  };

  return (
    <Container
      style={{
        backgroundColor: "white",
        height: "500px",
        padding: "20px",
        marginTop: "40px",
      }}
    >
      <h3>Termini pregleda</h3>
      <Table bordered striped>
        <thead className="thead-dark">
          <tr>
            <th>Datum</th>
            <th>Vreme</th>
            <th>Pacijent</th>
            <th>JMBG</th>
            <th>status</th>
            <th>Izveštaj</th>
            <th>Preuzimanje</th>
          </tr>
        </thead>
        <tbody>
          {appointments.length === 0 ? (
            <tr>
              <td className="text-center" colSpan={5}>
                Нема доступних прегледа!
              </td>
            </tr>
          ) : (
            appointments.map((appointment) => {
              return (
                <tr key={appointment.id}>
                  <td>{appointment.date}</td>
                  <td>{appointment.time}</td>
                  <td>
                    {appointment.citizen != null
                      ? `${appointment.citizen.name} ${appointment.citizen.lastname}`
                      : ""}
                  </td>
                  <td>
                    {appointment.citizen != null
                      ? appointment.citizen.identificationNumber
                      : ""}
                  </td>
                  <td>{appointment.statusTermina}</td>
                  <td>
                    {appointment.statusTermina == "ZAKAZAN" ? (
                      <ModalReport
                        appointmentId={appointment.id}
                        saveAppointmentReport={saveAppointmentReport}
                      />
                    ) : (
                      ""
                    )}
                  </td>
                  <td>
                    {appointment.statusTermina == "ZAVRSEN" ? (
                      <ReportPDFAppointment
                        appointment={appointment}
                        appointmentId={appointment.id}
                      />
                    ) : (
                      ""
                    )}
                  </td>
                </tr>
              );
            })
          )}
        </tbody>
      </Table>
    </Container>
  );
}

export default TableAppointments;
