import axios from "axios";

const baseURL = "http://localhost:5001/api/appointments";

export const AppointmentService = {
    getFreeAppointments,
    bookAppointment,
    getAppoitmentByUser,
    getAppointmentReport,
}



async function getFreeAppointments(){
    return await axios.get(baseURL+'/free');
}

async function bookAppointment(id){
    return await axios.put(baseURL + "/" + id);
}

async function getAppoitmentByUser(){
    return await axios.get(baseURL);
}

async function getAppointmentReport(id){
    return await axios.get(baseURL + "/" + id + "/reports");
}

