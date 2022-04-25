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
public class DrivingLicenceChangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    private DrivingLicenceType drivingLicenceType;

    @ManyToOne
    @JoinColumn(name = "driving_licence_id")
    private DrivingLicence drivingLicence;


    // Korisnik vec postoji u vozackoj
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
