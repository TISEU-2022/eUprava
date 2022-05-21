package ftn.euprava.mupvozila.model;

import ftn.euprava.mupvozila.model.enums.FuelType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//Lombok end
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String chassisNumber;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer engine;

    @Column(nullable = false)
    private Integer horsePower;

    @Column(nullable = false)
    private Integer weight;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "registration_certificate_id")
    private RegistrationCertificate registrationCertificate;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

}
