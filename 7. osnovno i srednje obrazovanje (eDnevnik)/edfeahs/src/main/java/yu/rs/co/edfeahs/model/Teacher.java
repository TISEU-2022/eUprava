package yu.rs.co.edfeahs.model;

import javax.persistence.*;
import java.util.List;

@Entity @DiscriminatorValue("ROLE_TEACHER") // Jakarta
public class Teacher extends Person {


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<Subject> subjects = new java.util.ArrayList<>();

}
