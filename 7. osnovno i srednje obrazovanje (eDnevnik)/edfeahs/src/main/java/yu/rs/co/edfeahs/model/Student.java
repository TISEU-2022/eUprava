package yu.rs.co.edfeahs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter// Lombok
@Entity @DiscriminatorValue("ROLE_STUDENT") // Jakarta
public class Student extends Person {

    // Fields
    private Integer grade;

    @ToString.Exclude
    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Attendance> attendances = new LinkedHashSet<>();


    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<GradeAverage> gradeAverages = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "elementary_school_id")
    private School elementarySchool;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "high_school_id")
    private School highSchool;

    @Override
    public String toString() {
        return "Student{} " + super.toString();
    }
}
