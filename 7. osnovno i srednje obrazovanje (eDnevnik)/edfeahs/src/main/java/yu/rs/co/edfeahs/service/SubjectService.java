package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.rs.co.edfeahs.model.Subject;
import yu.rs.co.edfeahs.model.Teacher;
import yu.rs.co.edfeahs.repository.SubjectRepository;
import yu.rs.co.edfeahs.repository.TeacherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;


    public List<Subject> findSubjectsByTeacherUCN(String teacherUCN) {
        Teacher teacher = teacherRepository.findTeacherByUCN(teacherUCN);
        return subjectRepository.findSubjectByTeacher_Id(teacher.getId());
    }
}
