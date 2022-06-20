package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.ReportRequest;
import ftn.euprava.zdravstvo.exception.BadRequestException;
import ftn.euprava.zdravstvo.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    private ResponseEntity addReport(@Validated @RequestBody ReportRequest reportRequest) throws BadRequestException{
        try {
            Long id = reportService.addReport(reportRequest);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();

            return ResponseEntity.created(location).build();
        }
        catch (BadRequestException e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
