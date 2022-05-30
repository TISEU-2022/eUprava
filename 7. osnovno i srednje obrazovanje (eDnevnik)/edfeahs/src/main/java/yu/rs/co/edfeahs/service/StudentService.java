package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.web.dto.StudentSearchParam;
import yu.rs.co.edfeahs.repository.StudentRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public List<Student> findStudents(StudentSearchParam searchParam) {
        System.out.println(searchParam);
        return studentRepository.findStudents(searchParam);
//        return studentRepository.findStudentByName(searchParam.getStudentName());
    }
}
