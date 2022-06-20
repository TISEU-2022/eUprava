package ftn.euprava.zdravstvo.api.dto;

import ftn.euprava.zdravstvo.model.AppointmentReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportResponse {
    private Long id;
    private String message;
    private Long appointmentId;

    public ReportResponse(AppointmentReport appointmentReport) {
        this.id = appointmentReport.getId();
        this.message = appointmentReport.getReport();
        this.appointmentId = appointmentReport.getAppointment().getId();
    }
}
