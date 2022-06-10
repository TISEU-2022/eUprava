package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentResponse {
    private Long id;
    private Date date;
    private DoctorDTO doctor;
    private CitizenDTO citizen;
    private String report;
}
