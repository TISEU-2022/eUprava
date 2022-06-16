package ftn.euprava.zdravstvo.api.dto;

import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.StatusTermina;
import ftn.euprava.zdravstvo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentResponseDoctor {
    private Long id;
    private User citizen;
    private User doctor;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String statusTermina;


    public AppointmentResponseDoctor(Appointment appointment){
        this.id = appointment.getId();
        this.citizen = appointment.getCitizen();
        this.doctor = appointment.getDoctor();
        this.date = appointment.getDatum();
        this.time = appointment.getVreme();
        this.description = appointment.getDescription();
        this.statusTermina = appointment.getStatusTermina().toString();
    }
}
