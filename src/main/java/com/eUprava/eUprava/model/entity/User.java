package com.eUprava.eUprava.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(appliesTo = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String identificationNumber;
    private String name;
    private String lastname;
    private String gender;
    private String citizenship;
    private String address;
    private String cityOfBirth;
    private LocalDate dateOfBirth;
    private String countryOfBirth;
    private String email;
    private String role;
}
