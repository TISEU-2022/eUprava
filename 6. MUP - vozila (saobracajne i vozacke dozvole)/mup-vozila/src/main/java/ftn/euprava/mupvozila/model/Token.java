package ftn.euprava.mupvozila.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//Lombok end
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 512,nullable = false)
    private String token;

}
