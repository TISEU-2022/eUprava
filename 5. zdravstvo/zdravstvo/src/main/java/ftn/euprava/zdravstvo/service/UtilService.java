package ftn.euprava.zdravstvo.service;

import ftn.euprava.zdravstvo.model.Appointment;
import ftn.euprava.zdravstvo.model.StatusTermina;
import ftn.euprava.zdravstvo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

@Service
public class UtilService {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    public void createUsers(){
        User user = new User();
        user.setUsername("pero");
        user.setRole("PATIENT");
        user.setAddress("Novi Sad");
        user.setCitizenship("Srbija");
        user.setEmail("pero@gmail.com");
        user.setIdentificationNumber("1409988172850");
        user.setName("Petar");
        user.setLastname("Petrovic");
        user.setCityOfBirth("Novi Sad");
        user.setCountryOfBirth("Srbija");
        user.setDateOfBirth(LocalDate.of(1988,9,14));
        user.setGender("Musko");

        User user2 = new User();
        user2.setUsername("jovo");
        user2.setRole("DOCTOR");
        user2.setAddress("Novi Sad");
        user2.setCitizenship("Srbija");
        user2.setEmail("jovo@gmail.com");
        user2.setIdentificationNumber("1409999170850");
        user2.setName("Jovan");
        user2.setLastname("Peric");
        user2.setCityOfBirth("Novi Sad");
        user2.setCountryOfBirth("Srbija");
        user2.setDateOfBirth(LocalDate.of(1999,9,14));
        user2.setGender("Musko");
        userService.save(user);
        userService.save(user2);

        Appointment appointment = new Appointment(user,user2,"Kardiolog",LocalDate.of(2022,Month.JUNE,10),
                LocalTime.of(10,0,0), StatusTermina.ZAKAZAN);

        Appointment appointment2 = new Appointment(user,user2,"Kardiolog",LocalDate.of(2022, Month.JUNE,11),
                LocalTime.of(14,30,0), StatusTermina.ZAKAZAN);

        Appointment appointment3 = new Appointment(null,user2,"Kardiolog",LocalDate.of(2022,Month.JUNE,10),
                LocalTime.of(12,30,0), StatusTermina.SLOBODAN);

        appointmentService.save(appointment);
        appointmentService.save(appointment2);
        appointmentService.save(appointment3);

    }
}
