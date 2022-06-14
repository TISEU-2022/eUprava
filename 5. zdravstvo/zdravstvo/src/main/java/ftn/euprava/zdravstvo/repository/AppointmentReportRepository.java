package ftn.euprava.zdravstvo.repository;

import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.AppointmentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentReportRepository extends JpaRepository<AppointmentReport, Long> {

    AppointmentReport findByAppointment_Id(Long appointmentId);
}
