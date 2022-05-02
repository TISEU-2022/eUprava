package ftn.euprava.mupvozila.model;

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
public class RegistrationCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    private String licensePlate;

    @Column
    private LocalDate dayOfIssue;

    @Column
    private String placeOfIssue;

    @Column
    private Boolean request;

    private Boolean status = null;

    @Column(nullable = false)
    private String userId;

    @OneToOne(mappedBy = "registrationCertificate", orphanRemoval = true)
    private Car car;
}
