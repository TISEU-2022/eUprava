package ftn.euprava.zdravstvo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    private String purpose;

    private String content;

    public Certificate(User user, String content, String purpose) {
        this.user = user;
        this.content = content;
        this.purpose = purpose;
    }
}