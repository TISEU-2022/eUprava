package ftn.euprava.mupvozila.web.controller;

import com.google.gson.Gson;
import ftn.euprava.mupvozila.model.enums.RequestStatus;
import ftn.euprava.mupvozila.service.IDrivingLicenceChangeRequestService;
import ftn.euprava.mupvozila.service.IDrivingLicenceService;
import ftn.euprava.mupvozila.service.IRequestForDrivingLicenceService;
import ftn.euprava.mupvozila.util.jwt.JwtTokenUtil;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceChangeRequestDTO;
import ftn.euprava.mupvozila.web.dto.DrivingLicenceDTO;
import ftn.euprava.mupvozila.web.dto.RequestForDrivingLicenceDTO;
import ftn.euprava.mupvozila.web.dto.UserDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
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
    private final JwtTokenUtil jwtTokenUtil;
    private final int itemsPerPage = 5;

    public DrivingLicenceController(IDrivingLicenceService iDrivingLicenceService, IDrivingLicenceChangeRequestService iDrivingLicenceChangeRequestService,
                                    IRequestForDrivingLicenceService iRequestForDrivingLicenceService, JwtTokenUtil jwtTokenUtil) {
        this.iDrivingLicenceService = iDrivingLicenceService;
        this.iDrivingLicenceChangeRequestService = iDrivingLicenceChangeRequestService;
        this.iRequestForDrivingLicenceService = iRequestForDrivingLicenceService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

//    @GetMapping(value = "/user/{identityNumber}")
//    public ResponseEntity<DrivingLicenceDTO> getDrivingLicenceByIdentityNumber(@PathVariable String identityNumber){
//        return new ResponseEntity<>(iDrivingLicenceService.findOneByIdentityNumber(identityNumber), HttpStatus.OK);
//    }

    // medical certificate
    @GetMapping(value = "/create/medical-certificate")
    public ResponseEntity<String> createDrivingLicence(HttpServletRequest request){

        String header = request.getHeader("Authorization");
        String token = header.substring(7);

        String id = this.jwtTokenUtil.getUserId(token);
        String uri = "http://auth-app:3101/user/find/id/"+id;

        OkHttpClient client = new OkHttpClient();
        Request authRequest = new Request.Builder()
                .url(uri)
                .addHeader("Authorization","Bearer " + token)
                .build();

        try {
            Response authResponse = client.newCall(authRequest).execute();
            System.out.println("Auth server response:"+authResponse.peekBody(2048).string());

            Gson gson = new Gson();
            UserDTO userData = gson.fromJson(authResponse.peekBody(2048).string(), UserDTO.class);



            if (authResponse.code() == 200 || authResponse.code() == 201){

                String requestDataString = "{ \"name\": \""+userData.getFirstName()+"\", \"lastName\": \""+userData.getLastName()+"\", " +
                        "\"jmbg\": \""+userData.getIdentityNumber()+"\", \"purpose\": \"izdavanje vozacke dozvole\" }";

                System.out.println("Request data for medical certificate: "+ requestDataString);

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, requestDataString);

                uri = "http://host.docker.internal:5001/api/medical-certificates";

                Request zdravstvoRequest = new Request.Builder()
                        .url(uri)
                        .post(body)
                        //.addHeader("Authorization","Bearer " + token)
                        .build();

                try{
                    Response zdravstvoResponse = client.newCall(zdravstvoRequest).execute();
                    System.out.println("Zdravstvo response: "+zdravstvoResponse.peekBody(2048).string());

                    if (zdravstvoResponse.code() == 200 || zdravstvoResponse.code() == 201){
                        // SUCCESSFUL
                        return new ResponseEntity<String>(zdravstvoResponse.peekBody(2048).string(),HttpStatus.CREATED);
                    }
                    // else
                    return new ResponseEntity<String>("There's been an error while creating medical certificate",
                            HttpStatus.CREATED);

                }
                catch (Exception e){
                    e.printStackTrace();
                    return new ResponseEntity<String>(e.toString(),HttpStatus.CREATED);
                }


            }
            // else
            return new ResponseEntity<String>("There's been an error while fetching user data",
                    HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.toString(),HttpStatus.BAD_REQUEST);
        }

    }

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
