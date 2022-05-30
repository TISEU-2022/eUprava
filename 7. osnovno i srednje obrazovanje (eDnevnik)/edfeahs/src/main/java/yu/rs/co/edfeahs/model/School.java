package yu.rs.co.edfeahs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import yu.rs.co.edfeahs.model.enums.InstitutionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter // Lombok
@Entity // Jakarta
public class School {

    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fields
    private String name;

    @Enumerated(EnumType.STRING)
    private InstitutionType type;

    // Navigationals

    @OneToMany(mappedBy = "school", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    private List<Subject> subjects = new ArrayList<>();


}
