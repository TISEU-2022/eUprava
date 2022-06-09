package ftn.euprava.zdravstvo.service;

import ftn.euprava.zdravstvo.api.dto.AppoinmentReportResponse;
import ftn.euprava.zdravstvo.api.dto.AppointmentRequest;
import ftn.euprava.zdravstvo.api.dto.AppointmentResponse;
import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.AppointmentReport;
import ftn.euprava.zdravstvo.model.StatusTermina;
import ftn.euprava.zdravstvo.model.User;
import ftn.euprava.zdravstvo.repository.AppointmentReportRepository;
import ftn.euprava.zdravstvo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentReportRepository appointmentReportRepository;

    public List<AppointmentResponse> getAppointmentsByUser() {
        List<AppointmentResponse> appointments = new ArrayList<>();
        //find user by login
        User user=null;

        for(Appointment app: appointmentRepository.findAllByCitizen(user)){
            appointments.add(new AppointmentResponse(app));
        }

        return appointments;
    }

    public List<AppointmentResponse> getFreeAppointments() {
        List<AppointmentResponse> appointments = new ArrayList<>();

        for(Appointment app: appointmentRepository.findAll()){
            if(app.getStatusTermina() == StatusTermina.SLOBODAN){
                appointments.add(new AppointmentResponse(app));
            }
        }

        return appointments;
    }

    public void bookAppointment(Long appointmentId) {
        //find user by login
        User user=null;

        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        assert appointment != null;
        appointment.setStatusTermina(StatusTermina.ZAKAZAN);
        appointment.setCitizen(user);
        appointmentRepository.save(appointment);

    }

    public AppoinmentReportResponse getAppointmentReport(Long appointmentId){

        AppointmentReport appointmentReport = appointmentReportRepository.findByAppointment_Id(appointmentId);
        AppoinmentReportResponse response = new AppoinmentReportResponse();

        if(appointmentReport == null){
            response.setReport("JOŠ NEMA IZVEŠTAJA!");
        }else{
            response.setReport(appointmentReport.getReport());
            response.setId(appointmentReport.getId());
        }

        return response;
    }

    public AppointmentResponse create(AppointmentRequest request) {
        return null;
    }
}
