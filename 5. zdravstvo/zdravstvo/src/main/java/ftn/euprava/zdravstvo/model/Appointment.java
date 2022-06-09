package ftn.euprava.zdravstvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User citizen;
    @ManyToOne
    private User doctor;
    private String description;
    private LocalDate datum;
    private LocalTime vreme;
    private StatusTermina statusTermina;





}