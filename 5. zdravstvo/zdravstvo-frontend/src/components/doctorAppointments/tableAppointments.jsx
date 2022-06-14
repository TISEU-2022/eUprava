import React, { useEffect, useState } from "react";
import { Container, Table } from "react-bootstrap";
import MUIDataTable from "mui-datatables";
import { AppointmentService } from "../../services/AppointmentService";
import { AppointmentReportService } from "../../services/AppointmentReportService";
import { Typography } from "@mui/material";
import ModalReport from "../modal";
import Swal from "sweetalert2";

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
            appointments.map((a) => {
              return (
                <tr key={a.id}>
                  <td>{a.date}</td>
                  <td>{a.time}</td>
                  <td>
                    {a.citizen != null
                      ? `${a.citizen.name} ${a.citizen.lastname}`
                      : ""}
                  </td>
                  <td>
                    {a.citizen != null ? a.citizen.identificationNumber : ""}
                  </td>
                  <td>{a.statusTermina}</td>
                  <td>
                    {a.statusTermina == "ZAKAZAN" ? (
                      <ModalReport
                        appointmentId={a.id}
                        saveAppointmentReport={saveAppointmentReport}
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
