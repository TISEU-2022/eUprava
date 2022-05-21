package ftn.euprava.mupvozila.model;

import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.model.enums.RequestType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//Lombok end
@Entity
public class DrivingLicenceChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @ManyToOne
    @JoinColumn(name = "driving_licence_id")
    private DrivingLicence drivingLicence;

    @Column(nullable = false)
    private String employeeId;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;


    // Korisnik vec postoji u vozackoj
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
