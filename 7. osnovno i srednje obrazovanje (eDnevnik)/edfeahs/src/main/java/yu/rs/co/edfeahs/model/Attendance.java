package yu.rs.co.edfeahs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter @ToString // Lombok
@Entity // Jakarta
public class Attendance {

    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Double firstSemesterAvg;

    private Double secondSemesterAvg;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
    Set<Mark> marks = new LinkedHashSet<>();

    private boolean firstSemesterFinalized = false;

    private boolean secondSemesterFinalized = false;
}
