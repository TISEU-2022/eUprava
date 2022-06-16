import logo from "./logo.svg";
import "./App.css";
import React from 'react';
import { BrowserRouter, Route, Routes, Outlet } from "react-router-dom";
import Home from "./pages/Home";
import TokenHandler from "./components/TokenHandler";
import CitizenManipulation from "./components/citizen-manipulation/CitizenManipulation";
import BookAppointment from "./components/appointments/BookAppointment";
import NavBar from "./components/NavBar";
import AppointmentHistory from "./components/appointments/AppointmentHistory";
import DoctorAppointmentsPage from "./pages/DoctorAppointmentsPage";
import AppointmentReport from "./components/doctorAppointments/appointmentReport";

function App() {
  return (

    <BrowserRouter>
      <NavBar />

      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
        </Route>
        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
        </Route>
        <Route
          exact
          path="/citizen-manipulation"
          element={<CitizenManipulation />}
        />
        <Route
          exact
          path="/book-appointment"
          element={<BookAppointment />}
        />

        <Route
          exact
          path="/medical-record"
          element={<AppointmentHistory />}
        />
        <Route
          exact
          path="/doctor-appointments"
          element={<DoctorAppointmentsPage />}
        />
        <Route
          exact
          path="/doctor-appointments/appointment/:id/report"
          element={<AppointmentReport />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
