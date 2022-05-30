package com.euprava.izradadokumenata.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "document")
@Entity(name = "document")
public class Document extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate issuedOn;

    @Column
    private LocalDate expirationDate;

    private String issuingAuthority;

    @Column
    private DocumentType documentType;

}
