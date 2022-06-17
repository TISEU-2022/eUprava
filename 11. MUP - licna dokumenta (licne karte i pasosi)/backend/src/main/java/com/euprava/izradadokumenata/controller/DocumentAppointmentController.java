package com.euprava.izradadokumenata.controller;


import com.euprava.izradadokumenata.model.DocumentAppointment;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentDto;
import com.euprava.izradadokumenata.model.dto.documentAppointment.DocumentAppointmentUserDto;
import com.euprava.izradadokumenata.model.dto.documentAppointment.SimpleDocumentAppointmentDto;
import com.euprava.izradadokumenata.model.dto.user.LoggedUserDto;
import com.euprava.izradadokumenata.model.dto.user.UserMapper;
import com.euprava.izradadokumenata.model.dto.user.UserSetupDto;
import com.euprava.izradadokumenata.service.DocumentAppointmentService;
import com.euprava.izradadokumenata.service.UserService;
import com.euprava.izradadokumenata.util.exceptions.AppointmentTimeNotAvailableException;
import com.euprava.izradadokumenata.util.exceptions.UserMissingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@AllArgsConstructor
@Slf4j
public class DocumentAppointmentController {

    private final DocumentAppointmentService documentAppointmentService;

    private final UserService userService;

    @PostMapping("/initialRequest")
    public ResponseEntity<Boolean> isInitial(@RequestBody LoggedUserDto loggedUserDto) {
        return new ResponseEntity<>(userService.initialSetup(loggedUserDto), HttpStatus.OK);
    }

    @GetMapping("/myAppointments")
    public ResponseEntity<List<DocumentAppointmentUserDto>> userAppointments(@RequestBody LoggedUserDto loggedUserDto) {
        List<DocumentAppointmentUserDto> appointmentUserDtoList;
        try {
            appointmentUserDtoList = documentAppointmentService.getAllAppointmentsForUser(loggedUserDto.getUsername());
        } catch (UserMissingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(appointmentUserDtoList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity fetchAll() {
        return new ResponseEntity(documentAppointmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/userData")
    public ResponseEntity<String> userSetup(@RequestBody UserSetupDto userSetupDto) {
        try {
            userService.userDataSetup(userSetupDto);
        } catch (UserMissingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/newAppointment")
    public ResponseEntity<SimpleDocumentAppointmentDto> requestAppointment(@RequestBody DocumentAppointmentDto documentAppointmentDto, @RequestParam String loggedUsername) {
        try {
            if (documentAppointmentService.isAppointmentAvailable(documentAppointmentDto.getRequestedAppointmentTime(), documentAppointmentDto.getDocumentType())) {
                if (documentAppointmentDto.isAppointmentForMinor()) {
                    DocumentAppointment doc = documentAppointmentService.appointmentForMinor(loggedUsername, documentAppointmentDto);
                    SimpleDocumentAppointmentDto dtoEntity = SimpleDocumentAppointmentDto.builder()
                            .user(UserMapper.INSTANCE.toSimpleDto(userService.getUserByUsername(loggedUsername)))
                            .appointmentTime(doc.getRequestedAppointmentTime()).appointmentForMinor(doc.isAppointmentForMinor()).build();
                    return new ResponseEntity<>(dtoEntity, HttpStatus.OK);
                } else {
                    DocumentAppointment doc = documentAppointmentService.appointmentForSelf(loggedUsername, documentAppointmentDto);
                    SimpleDocumentAppointmentDto dtoEntity = SimpleDocumentAppointmentDto.builder()
                            .user(UserMapper.INSTANCE.toSimpleDto(userService.getUserByUsername(loggedUsername)))
                            .appointmentTime(doc.getRequestedAppointmentTime()).appointmentForMinor(doc.isAppointmentForMinor()).build();
                    return new ResponseEntity<>(dtoEntity, HttpStatus.OK);
                }
            } else throw new AppointmentTimeNotAvailableException();
        } catch (AppointmentTimeNotAvailableException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
