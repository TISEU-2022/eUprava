package ftn.euprava.zdravstvo.repository;

import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByCitizen(User user);

    List<Appointment> findAllByDoctor(User user);

    List<Appointment> findAllByDoctorAndDatum(User user, LocalDate datum);
}
