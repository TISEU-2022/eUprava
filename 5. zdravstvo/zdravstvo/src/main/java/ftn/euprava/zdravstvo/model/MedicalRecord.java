package ftn.euprava.zdravstvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @OneToMany(mappedBy="record-reports")
//    private List<AppointmentReport> reports = new ArrayList<>();

    @ManyToOne
    private User citizen;
}
