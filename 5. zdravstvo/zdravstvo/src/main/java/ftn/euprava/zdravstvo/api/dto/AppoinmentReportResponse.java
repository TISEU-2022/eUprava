package ftn.euprava.zdravstvo.api.dto;

import ftn.euprava.zdravstvo.model.AppointmentReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppoinmentReportResponse {

    private Long id;
    private String report;


    public AppoinmentReportResponse(AppointmentReport appointmentReport){
        this.id = appointmentReport.getId();
        this.report = appointmentReport.getReport();
    }
}
