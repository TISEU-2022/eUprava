package ftn.euprava.zdravstvo.repository;

import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByCitizen(User user);
}
