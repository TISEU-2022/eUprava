package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.rs.co.edfeahs.model.Subject;
import yu.rs.co.edfeahs.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> findSubjectsByTeacherId(Long teacherId) {
        return subjectRepository.findSubjectByTeacher_Id(teacherId);
    }
}
