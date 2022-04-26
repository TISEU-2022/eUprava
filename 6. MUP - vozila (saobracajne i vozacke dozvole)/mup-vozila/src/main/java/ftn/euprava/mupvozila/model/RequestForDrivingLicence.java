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

    @Column(nullable = false)
    private String citizenId;

    @Column(nullable = false)
    private String employeeId;
}
