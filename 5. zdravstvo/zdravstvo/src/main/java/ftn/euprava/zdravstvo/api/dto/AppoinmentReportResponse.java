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

}
