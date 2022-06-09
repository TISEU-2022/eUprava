package ftn.euprava.zdravstvo.api.dto;

import ftn.euprava.zdravstvo.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentResponse {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String doctorName;
    private String description;


    public AppointmentResponse(Appointment appointment){
        this.id = appointment.getId();
        this.date = appointment.getDatum();
        this.time = appointment.getVreme();
        this.doctorName = appointment.getDoctor().getName() + " " + appointment.getDoctor().getLastname();
        this.description = appointment.getDescription();
    }

}
