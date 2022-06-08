package com.euprava.izradadokumenata.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
public class DocumentAppointment extends BaseEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @ManyToOne
    private Document document;

    @ManyToOne
    private User user;

    @Column
    private LocalDateTime requestedAppointmentTime;

    @Column
    private boolean available;

    private boolean appointmentForMinor;

    @Nullable
    @ManyToOne
    private User additionalUser;


}
