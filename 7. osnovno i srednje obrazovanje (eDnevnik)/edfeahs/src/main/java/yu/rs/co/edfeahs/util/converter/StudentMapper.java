package yu.rs.co.edfeahs.util.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.model.Subject;
import yu.rs.co.edfeahs.repository.SubjectRepository;
import yu.rs.co.edfeahs.service.StudentService;
import yu.rs.co.edfeahs.web.dto.FoundStudentDto;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final StudentService studentService;
    private final SubjectRepository subjectRepository;

    public FoundStudentDto toFoundStudentDto(Student student, Long subjectId) {
        FoundStudentDto foundStudentDto = new FoundStudentDto();
        foundStudentDto.setId(student.getId());
        foundStudentDto.setUCN(student.getUCN());
        foundStudentDto.setFirstName(student.getFirstName());
        foundStudentDto.setLastName(student.getLastName());
        foundStudentDto.setGrade(student.getGrade());

        if(subjectId == null || subjectId == 0) {
            foundStudentDto.setAverageSubjectMark(0.0);
        } else {
            Subject subject = subjectRepository.findById(subjectId).get();
            foundStudentDto.setAverageSubjectMark(studentService.averageStudentsSubjectMark(student, subject));
        }

        return foundStudentDto;
    }
}
