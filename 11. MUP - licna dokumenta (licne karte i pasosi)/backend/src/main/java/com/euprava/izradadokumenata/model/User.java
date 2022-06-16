package com.euprava.izradadokumenata.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String jmbg;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private String countryOfBirth;

    @Column
    private String citizenship;

    @Column(columnDefinition = "boolean default true")
    private boolean initialRequest = true;

    @Column
    private String gender;

    @Column
    private Municipality municipality;

    private String username;

    private String password;

}
