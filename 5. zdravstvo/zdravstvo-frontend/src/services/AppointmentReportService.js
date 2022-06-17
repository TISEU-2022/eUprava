import AxiosClient from "./AxiosClient";

const baseURL = "http://localhost:5001/api/reports";

export const AppointmentReportService = {
    createAppointmentReport,

}

async function createAppointmentReport(appointmentRequest) {
    return await AxiosClient.post(baseURL, appointmentRequest)
}