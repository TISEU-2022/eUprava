package ftn.euprava.mupvozila.model;

import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
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

    @Column
    private String employeeId;

    @OneToOne
    @JoinColumn(name = "driving_licence_id")
    private DrivingLicence drivingLicence;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

}
