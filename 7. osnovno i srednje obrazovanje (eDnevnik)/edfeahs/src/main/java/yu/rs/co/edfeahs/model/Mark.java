package yu.rs.co.edfeahs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;
import org.springframework.transaction.annotation.Transactional;
import yu.rs.co.edfeahs.model.enums.Semester;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString// Lombok
@Entity // Jakarta
public class Mark {

    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "attendance_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Attendance attendance;

    private Integer value;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    private LocalDateTime date = LocalDateTime.now();


}
