package yu.rs.co.edfeahs.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import yu.rs.co.edfeahs.model.Mark;
import yu.rs.co.edfeahs.service.MarkService;
import yu.rs.co.edfeahs.util.converter.MarkMapper;
import yu.rs.co.edfeahs.web.dto.CreateMarkDto;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/marks")
public class MarkController {
    private final MarkService markService;
    private final MarkMapper markMapper;

    @PostMapping
    public ResponseEntity<Mark> create(@Valid CreateMarkDto createMarkDto) {

        Mark result = markService.saveMark(markMapper.toEntity(createMarkDto));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}
