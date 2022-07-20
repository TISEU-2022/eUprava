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
import yu.rs.co.edfeahs.web.dto.MarkSearchParam;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/marks")
public class MarkController {
    private final MarkService markService;
    private final MarkMapper markMapper;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Mark> create(@Valid @RequestBody CreateMarkDto createMarkDto) {
        System.out.println("Create mark " + createMarkDto);

        Mark result = markService.saveMark(markMapper.toEntity(createMarkDto));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mark>> findMarks(@Valid MarkSearchParam searchParam) {
        System.out.println("natalyyy");
        System.out.println(searchParam);
        List<Mark> result = markService.findMarks(searchParam);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(consumes = "application/json")
    public ResponseEntity<ResponseEntity> deleteMarks(@RequestBody Integer[] deletedMarks) {
        System.out.println("Deleted marks!");
        System.out.println(deletedMarks[0]);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
