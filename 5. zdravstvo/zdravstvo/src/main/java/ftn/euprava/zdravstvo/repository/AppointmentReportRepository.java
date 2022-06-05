package ftn.euprava.zdravstvo.repository;

import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.AppointmentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentReportRepository extends JpaRepository<AppointmentReport, Long> {

    AppointmentReport findByAppointment_Id(Long appointmentId);
}
