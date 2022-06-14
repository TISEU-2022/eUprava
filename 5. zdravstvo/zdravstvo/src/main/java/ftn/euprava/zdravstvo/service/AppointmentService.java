package ftn.euprava.zdravstvo.service;

import ftn.euprava.zdravstvo.api.dto.AppoinmentReportResponse;
import ftn.euprava.zdravstvo.api.dto.AppointmentRequest;
import ftn.euprava.zdravstvo.api.dto.AppointmentResponse;
import ftn.euprava.zdravstvo.api.dto.AppointmentResponseDoctor;
import ftn.euprava.zdravstvo.exception.BadRequestException;
import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.AppointmentReport;
import ftn.euprava.zdravstvo.model.StatusTermina;
import ftn.euprava.zdravstvo.model.User;
import ftn.euprava.zdravstvo.repository.AppointmentReportRepository;
import ftn.euprava.zdravstvo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentReportRepository appointmentReportRepository;

    public List<AppointmentResponse> getAppointmentsByUser(Authentication authentication) {
        List<AppointmentResponse> appointments = new ArrayList<>();
        User user= userService.findByUsername(authentication.getName());

        for(Appointment app: appointmentRepository.findAllByCitizen(user)){
            appointments.add(new AppointmentResponse(app));
        }

        return appointments;
    }

    public List<AppointmentResponseDoctor> getAppointmentsByDoctor(Authentication authentication) {

        List<AppointmentResponseDoctor> appointments = new ArrayList<>();
        User doctor = userService.findByUsername(authentication.getName());

        for(Appointment app: appointmentRepository.findAllByDoctor(doctor)){
            appointments.add(new AppointmentResponseDoctor(app));
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

    public void bookAppointment(Long appointmentId, Authentication authentication) {
        User user= userService.findByUsername(authentication.getName());


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

    public AppointmentResponse create(AppointmentRequest request, Authentication authentication)
            throws BadRequestException {
        if(validateDateAndTime(request)) {

            Appointment appointment = new Appointment();
            appointment.setDatum(request.getDate().toLocalDate());
            appointment.setVreme(request.getDate().toLocalTime().plusHours(2));
            appointment.setDoctor(userService.getLogged(authentication));
            appointment.setStatusTermina(StatusTermina.SLOBODAN);

            appointmentRepository.save(appointment);

            return new AppointmentResponse(appointment);
        }
        throw new BadRequestException("There is already an appointment at a given date and time");
    }

    public void save(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public Appointment findById(Long id){
        return appointmentRepository.findById(id).orElse(null);
    }

    public boolean validateDateAndTime(AppointmentRequest request){
        LocalDate date = request.getDate().toLocalDate();
        LocalTime time = request.getDate().toLocalTime().plusHours(2);
        List<Appointment> appointments = appointmentRepository.findAll();
        for(Appointment appointment : appointments){
            if(appointment.getDatum().equals(date) && appointment.getVreme().equals(time)){
                return false;
            }
        }
        return true;
    }
}
