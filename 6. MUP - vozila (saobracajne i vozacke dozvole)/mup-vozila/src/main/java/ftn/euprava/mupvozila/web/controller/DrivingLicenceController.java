package ftn.euprava.mupvozila.web.controller;

import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.service.IDrivingLicenceChangeRequestService;
import ftn.euprava.mupvozila.service.IDrivingLicenceService;
import ftn.euprava.mupvozila.service.IRequestForDrivingLicenceService;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/driving-licence")
public class DrivingLicenceController {

    private final IDrivingLicenceService iDrivingLicenceService;
    private final IDrivingLicenceChangeRequestService iDrivingLicenceChangeRequestService;
    private final IRequestForDrivingLicenceService iRequestForDrivingLicenceService;
    private final int itemsPerPage = 5;

    public DrivingLicenceController(IDrivingLicenceService iDrivingLicenceService, IDrivingLicenceChangeRequestService iDrivingLicenceChangeRequestService,
                                    IRequestForDrivingLicenceService iRequestForDrivingLicenceService) {
        this.iDrivingLicenceService = iDrivingLicenceService;
        this.iDrivingLicenceChangeRequestService = iDrivingLicenceChangeRequestService;
        this.iRequestForDrivingLicenceService = iRequestForDrivingLicenceService;
    }

//    @GetMapping(value = "/user/{identityNumber}")
//    public ResponseEntity<DrivingLicenceDTO> getDrivingLicenceByIdentityNumber(@PathVariable String identityNumber){
//        return new ResponseEntity<>(iDrivingLicenceService.findOneByIdentityNumber(identityNumber), HttpStatus.OK);
//    }

    //--------  DRIVING LICENCES  --------/
    @GetMapping(value = "/{id}")
    public ResponseEntity<DrivingLicenceDTO> getDrivingLicenceById(@PathVariable Long id){
        return new ResponseEntity<>(iDrivingLicenceService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "user/{userId}")
    public ResponseEntity<DrivingLicenceDTO> getDrivingLicenceByUserId(@PathVariable String userId){
        return new ResponseEntity<>(iDrivingLicenceService.findOneByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<DrivingLicenceDTO> createDrivingLicence(@RequestBody RequestForDrivingLicenceDTO requestForDrivingLicenceDTO){
        return new ResponseEntity<DrivingLicenceDTO>(iDrivingLicenceService.save(requestForDrivingLicenceDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<DrivingLicenceDTO> editDrivingLicence(@RequestBody DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO){
        return new ResponseEntity<DrivingLicenceDTO>(iDrivingLicenceService.update(drivingLicenceChangeRequestDTO), HttpStatus.OK);
    }


    //--------  DRIVING LICENCE REQUESTS  --------/
    @GetMapping(value = "/get/requests")
    public ResponseEntity<Page<RequestForDrivingLicenceDTO>> getRequestsForDrivingLicence(HttpServletRequest request,
                                                                                          @PathParam(value = "page") Integer page,
                                                                                          @PathParam(value = "requestStatus") RequestStatus requestStatus){
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        return new ResponseEntity<Page<RequestForDrivingLicenceDTO>>(iRequestForDrivingLicenceService.findAll(token, requestStatus, PageRequest.of(page, itemsPerPage)), HttpStatus.OK);
    }

    @GetMapping(value = "/get/edit-requests")
    public ResponseEntity<Page<DrivingLicenceChangeRequestDTO>> getRequestsForDrivingLicenceChange(HttpServletRequest request,
                                                                                                   @PathParam(value = "page") Integer page,
                                                                                                   @PathParam(value = "requestStatus") RequestStatus requestStatus){
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        return new ResponseEntity<Page<DrivingLicenceChangeRequestDTO>>(iDrivingLicenceChangeRequestService.findAll(token, requestStatus, PageRequest.of(page, itemsPerPage)), HttpStatus.OK);
    }

    @GetMapping(value = "/get/requests/{id}")
    public ResponseEntity<RequestForDrivingLicenceDTO> getRequestForDLbyId(@PathVariable Long id){
        return new ResponseEntity<RequestForDrivingLicenceDTO>(iRequestForDrivingLicenceService.findOne(id), HttpStatus.OK);
    }

    // used as a validation if user has already sent request for new driving licence
    // if so he won't be shown options for creating a new request
    @GetMapping(value = "/get/pending-request/citizen/{userId}")
    public ResponseEntity<RequestForDrivingLicenceDTO> getPendingRequestForDrivingLicenceForUser(@PathVariable String userId){
        return new ResponseEntity<RequestForDrivingLicenceDTO>(iRequestForDrivingLicenceService.findPendingRequest(userId), HttpStatus.OK);
    }

    // used as a validation if user has already sent request for driving licence change
    // if so he won't be shown options for creating a new change request
    @GetMapping(value = "/get/pending-edit-request/citizen/{userId}")
    public ResponseEntity<DrivingLicenceChangeRequestDTO> getPendingRequestForDrivingLicenceChangeForUser(@PathVariable String userId){
        return new ResponseEntity<DrivingLicenceChangeRequestDTO>(iDrivingLicenceChangeRequestService.findPendingRequest(userId), HttpStatus.OK);
    }

    // CREATING NEW REQUESTS (gradjanin)
    @PostMapping(value = "/create/request")
    public ResponseEntity<RequestForDrivingLicenceDTO> createRequestForDrivingLicence(@RequestBody RequestForDrivingLicenceDTO requestForDrivingLicenceDTO){
        return new ResponseEntity<RequestForDrivingLicenceDTO>(iRequestForDrivingLicenceService.save(requestForDrivingLicenceDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/create/edit-request")
    public ResponseEntity<DrivingLicenceChangeRequestDTO> createRequestForDrivingLicenceChange(@RequestBody DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO){
        return new ResponseEntity<DrivingLicenceChangeRequestDTO>(iDrivingLicenceChangeRequestService.save(drivingLicenceChangeRequestDTO), HttpStatus.CREATED);
    }

    // ACCEPTING OR DECLINING REQUESTS (admin, zaposleni)
    @PutMapping(value = "/edit/request")
    public ResponseEntity<RequestForDrivingLicenceDTO> editRequestForDrivingLicence(@RequestBody RequestForDrivingLicenceDTO requestForDrivingLicenceDTO){
        return new ResponseEntity<RequestForDrivingLicenceDTO>(iRequestForDrivingLicenceService.save(requestForDrivingLicenceDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/edit/edit-request")
    public ResponseEntity<DrivingLicenceChangeRequestDTO> editRequestForDrivingLicenceChange(@RequestBody DrivingLicenceChangeRequestDTO drivingLicenceChangeRequestDTO){
        return new ResponseEntity<DrivingLicenceChangeRequestDTO>(iDrivingLicenceChangeRequestService.save(drivingLicenceChangeRequestDTO), HttpStatus.OK);
    }

}
