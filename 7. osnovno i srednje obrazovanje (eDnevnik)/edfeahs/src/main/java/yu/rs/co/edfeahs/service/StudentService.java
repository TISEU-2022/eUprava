package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.rs.co.edfeahs.model.Attendance;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.model.Subject;
import yu.rs.co.edfeahs.repository.AttendanceRepository;
import yu.rs.co.edfeahs.repository.SubjectRepository;
import yu.rs.co.edfeahs.web.dto.StudentSearchParam;
import yu.rs.co.edfeahs.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AttendanceRepository attendanceRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findStudents(StudentSearchParam searchParam) {
        System.out.println(searchParam);
        return studentRepository.findStudents(searchParam);
//        return studentRepository.findStudentByName(searchParam.getStudentName());
    }

    public Double averageStudentsSubjectMark(Student student, Subject subject) {


        Attendance attendance = attendanceRepository.
                findAttendanceByStudentIdAndSubjectId(
                        student.getId(), subject.getId()
                );

        double result = attendance.getMarks().stream().mapToDouble(value -> value.getValue()).sum();

        return result;
    }
}
