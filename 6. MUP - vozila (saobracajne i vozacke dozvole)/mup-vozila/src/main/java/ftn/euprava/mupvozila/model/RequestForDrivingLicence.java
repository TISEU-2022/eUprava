package ftn.euprava.mupvozila.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//Lombok end
@Entity
public class RequestForDrivingLicence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrivingLicenceType drivingLicenceType;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "citizen_id")
    private User citizen;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private User employee;
}
