package ftn.euprava.mupvozila.model;

import ftn.euprava.mupvozila.model.enums.DrivingLicenceType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//Lombok end
@Entity
public class DrivingLicence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String licenceNumber;

    @Column
    private LocalDate dayOfIssue;

    @Column
    private LocalDate validUntil;

    @Column(nullable = false)
    private String placeOfIssue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrivingLicenceType drivingLicenceType;

    @Column(nullable = false)
    private String userId;

}
