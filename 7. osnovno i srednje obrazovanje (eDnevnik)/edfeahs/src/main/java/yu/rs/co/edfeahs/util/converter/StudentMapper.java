package yu.rs.co.edfeahs.util.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.model.Subject;
import yu.rs.co.edfeahs.repository.SubjectRepository;
import yu.rs.co.edfeahs.service.StudentService;
import yu.rs.co.edfeahs.service.SubjectService;
import yu.rs.co.edfeahs.web.dto.FoundStudentsDto;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final StudentService studentService;
    private final SubjectRepository subjectRepository;

    public FoundStudentsDto toFoundStudentDto(Student student, Long subjectId) {
        FoundStudentsDto foundStudentsDto = new FoundStudentsDto();
        foundStudentsDto.setId(student.getId());
        foundStudentsDto.setUCN(student.getUCN());
        foundStudentsDto.setFirstName(student.getFirstName());
        foundStudentsDto.setLastName(student.getLastName());

        if(subjectId == null || subjectId == 0) {
            foundStudentsDto.setAverageSubjectMark(0.0);
        } else {
            Subject subject = subjectRepository.findById(subjectId).get();
            foundStudentsDto.setAverageSubjectMark(studentService.averageStudentsSubjectMark(student, subject));
        }

        return foundStudentsDto;
    }
}
