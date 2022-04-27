package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.AppointmentRequest;
import ftn.euprava.zdravstvo.api.dto.AppointmentResponse;
import ftn.euprava.zdravstvo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAppointmentHistory(
            @RequestParam(defaultValue = "false") boolean history,
            @RequestParam(defaultValue = "false") boolean free
    ) {
        return ResponseEntity.ok().body(appointmentService.getAppointments());
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> bookAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok().body(appointmentService.bookAppointment(appointmentId));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(AppointmentRequest request)
            throws URISyntaxException {
        AppointmentResponse result = appointmentService.create(request);
        return ResponseEntity.created(new URI("/api/appointments/" + result.getId())).body(result);
    }
}
