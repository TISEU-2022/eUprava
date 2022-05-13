package com.euprava.izradadokumenata.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
@Table
public class User {

    @Id
    @Column
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

    @Column
    private String gender;

    @Column
    private Municipality municipality;



}
