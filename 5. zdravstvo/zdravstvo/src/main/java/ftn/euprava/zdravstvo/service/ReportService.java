package ftn.euprava.zdravstvo.service;

import ftn.euprava.zdravstvo.api.dto.ReportRequest;
import ftn.euprava.zdravstvo.api.dto.ReportResponse;
import ftn.euprava.zdravstvo.exception.BadRequestException;
import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.AppointmentReport;
import ftn.euprava.zdravstvo.model.StatusTermina;
import ftn.euprava.zdravstvo.repository.AppointmentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReportService {

    @Autowired
    private AppointmentReportRepository appointmentReportRepository;

    @Autowired
    private AppointmentService appointmentService;

    public Long addReport(ReportRequest reportRequest) throws BadRequestException {

        Appointment appointment = appointmentService.findById(reportRequest.getAppointmentId());
        String message = validateAppointment(appointment);
        if(message.equals("")) {

            appointment.setStatusTermina(StatusTermina.ZAVRSEN);
            appointmentService.save(appointment);

            AppointmentReport appointmentToSave = new AppointmentReport();
            appointmentToSave.setAppointment(appointment);
            appointmentToSave.setReport(reportRequest.getMessage());

            AppointmentReport savedReport = appointmentReportRepository.save(appointmentToSave);

            return savedReport.getId();
        }
        throw new BadRequestException(message);
    }

    private String validateAppointment(Appointment appointment){
        String message = "";
        if(appointment == null){
            message = "Appointment does not exist";
        }
        if(!appointment.getStatusTermina().equals(StatusTermina.ZAKAZAN)){
            message = "Već ste uneli izveštaj";
        }
        if(appointment.getDatum().isAfter(LocalDate.now())){
            message = "Pregled nije počeo";
        }
        if(appointment.getVreme().isAfter(LocalTime.now())){
            message = "Pregled nije počeo";
        }
        return message;
    }
}
