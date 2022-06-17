package com.euprava.izradadokumenata.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DocumentAppointment that = (DocumentAppointment) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
