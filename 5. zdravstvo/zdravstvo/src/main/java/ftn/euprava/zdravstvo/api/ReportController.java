package ftn.euprava.zdravstvo.api;

import ftn.euprava.zdravstvo.api.dto.ReportRequest;
import ftn.euprava.zdravstvo.api.dto.ReportResponse;
import ftn.euprava.zdravstvo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    private ResponseEntity addReport(ReportRequest request) throws URISyntaxException {
        ReportResponse result = reportService.addReport(request);
        ResponseEntity.created(new URI("/api/reports/" + result.getId())).body(result);
    }
}
