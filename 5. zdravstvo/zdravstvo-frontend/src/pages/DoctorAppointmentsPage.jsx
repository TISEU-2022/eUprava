import React from "react";
import AddAppointment from "../components/doctorAppointments/addAppointment";
import TableAppointments from "../components/doctorAppointments/tableAppointments";

function DoctorAppointmentsPage() {
  return (
    <>
      <AddAppointment></AddAppointment>
      <TableAppointments></TableAppointments>
    </>
  );
}

export default DoctorAppointmentsPage;
