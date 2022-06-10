import React, { useEffect, useState } from "react";
import { Container, Table } from "react-bootstrap";
import MUIDataTable from "mui-datatables";
import { AppointmentService } from "../../services/AppointmentService";
import { Typography } from "@mui/material";

const columns = [
  {
    name: "date",
    label: "datum",
    options: {
      filter: true,
      sort: true,
    },
  },
  {
    name: "time",
    label: "vreme",
    options: {
      filter: true,
      sort: false,
    },
  },
  {
    name: "citizen",
    label: "pacijent",
    options: {
      filter: true,
      sort: false,
      customBodyRender: (value, tableMeta, updateValue) => (
        <Typography>{value ? value.name : ""}</Typography>
      ),
    },
  },
  {
    name: "statusTermina",
    label: "status",
    options: {
      filter: true,
      sort: false,
    },
  },
];

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

  const options = { selectableRows: "none" };
  return (
    <Container
      style={{
        height: "500px",
        padding: "20px",
        marginTop: "40px",
      }}
    >
      <MUIDataTable
        title={"Termini"}
        data={appointments}
        columns={columns}
        options={options}
      />
    </Container>
  );
}

export default TableAppointments;
