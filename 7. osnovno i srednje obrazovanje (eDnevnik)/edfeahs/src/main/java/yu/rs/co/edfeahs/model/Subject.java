package yu.rs.co.edfeahs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import yu.rs.co.edfeahs.model.enums.Semester;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter // Lombok
@Entity // Jakarta
public class Subject {

    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Fields
    String name;

    Integer grade;


    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.REFRESH, orphanRemoval = true)
    Set<Attendance> attendances = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}
