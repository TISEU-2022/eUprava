package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.AppoinmentReportResponse;
import ftn.euprava.zdravstvo.api.dto.AppointmentRequest;
import ftn.euprava.zdravstvo.api.dto.AppointmentResponse;
import ftn.euprava.zdravstvo.api.dto.AppointmentResponseDoctor;
import ftn.euprava.zdravstvo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAppointmentHistory(Authentication authentication) {
        return ResponseEntity.ok().body(appointmentService.getAppointmentsByUser(authentication));
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<AppointmentResponseDoctor>> getAppointmentByLoggedDoctor(Authentication authentication,
                                                                                        @RequestParam(name = "datum", required = false, defaultValue = "") String datum) {
        return ResponseEntity.ok().body(appointmentService.getAppointmentsByDoctor(authentication, datum));
    }

    @GetMapping("/free")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<AppointmentResponse>> getFreeAppointments() {
        return ResponseEntity.ok().body(appointmentService.getFreeAppointments());
    }

    @GetMapping("/{appointment-id}/reports")
    public ResponseEntity<AppoinmentReportResponse> getAppointmentReport(@PathVariable("appointment-id") Long appointmentId){
        return ResponseEntity.ok().body(appointmentService.getAppointmentReport(appointmentId));

    }


    @PutMapping("/{appointment-id}")
    public void bookAppointment(@PathVariable("appointment-id") Long appointment_id, Authentication authentication) {
        appointmentService.bookAppointment(appointment_id, authentication);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody AppointmentRequest request, Authentication authentication)
            throws URISyntaxException {
        AppointmentResponse result = appointmentService.create(request, authentication);
        return ResponseEntity.created(new URI("/api/appointments/" + result.getId())).body(result);
    }
}
