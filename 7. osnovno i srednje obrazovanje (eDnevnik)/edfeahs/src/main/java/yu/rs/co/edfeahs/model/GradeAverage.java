package yu.rs.co.edfeahs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter // Lombok
@Entity // Jakarta
public class GradeAverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Double value;

    private Integer grade;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id")
    private Student student;

}
