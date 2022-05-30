package yu.rs.co.edfeahs.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yu.rs.co.edfeahs.model.Subject;
import yu.rs.co.edfeahs.service.SubjectService;
import yu.rs.co.edfeahs.service.TeacherService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final SubjectService subjectService;

    @GetMapping("{teacherId}/subjects")
    public ResponseEntity<List<Subject>> findTeachersSubjects(@PathVariable("teacherId") Long teacherId) {
        List<Subject> result = subjectService.findSubjectsByTeacherId(teacherId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
