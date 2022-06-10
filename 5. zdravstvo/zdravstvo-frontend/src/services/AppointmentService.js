import axios from "axios";
import AxiosClient from "./AxiosClient";

const baseURL = "http://localhost:5001/api/appointments";

export const AppointmentService = {
    getFreeAppointments,
    bookAppointment,
    getAppoitmentByUser,
    getAppointmentReport,
    getAppointmentsByDoctor,
    createFreeApointment
}



async function getFreeAppointments() {
    return await AxiosClient.get(baseURL + '/free');
}

async function getAppointmentsByDoctor(date) {
    if (date == undefined) date = ""
    return await AxiosClient.get(baseURL + '/doctor?datum=' + date);
}

async function createFreeApointment(appointmentRequest) {
    return await AxiosClient.post(baseURL, appointmentRequest)
}

async function bookAppointment(id) {
    return await AxiosClient.put(baseURL + "/" + id);
}

async function getAppoitmentByUser() {
    return await AxiosClient.get(baseURL);
}

async function getAppointmentReport(id) {
    return await AxiosClient.get(baseURL + "/" + id + "/reports");
}

