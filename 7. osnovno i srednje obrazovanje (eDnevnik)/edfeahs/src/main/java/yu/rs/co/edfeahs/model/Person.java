package yu.rs.co.edfeahs.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

@Getter @Setter // Lombok
@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Jakarta
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING) // Jakarta
@DiscriminatorOptions(force = true) // Jakarta
public abstract class Person {

    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fields
    @Column(length = 13)
    private String UCN;

    private String firstName;

    private String lastName;
}
