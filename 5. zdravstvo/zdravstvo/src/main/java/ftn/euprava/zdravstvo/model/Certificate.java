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

    private String userJmbg;

    private String message;

    public Certificate(String userJmbg, String message) {
        this.userJmbg = userJmbg;
        this.message = message;
    }
}
