package yu.rs.co.edfeahs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.model.Subject;
import yu.rs.co.edfeahs.model.Teacher;
import yu.rs.co.edfeahs.repository.AttendanceRepository;
import yu.rs.co.edfeahs.repository.StudentRepository;
import yu.rs.co.edfeahs.repository.SubjectRepository;
import yu.rs.co.edfeahs.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    private final AttendanceRepository attendanceRepository;


    public List<Subject> findSubjectsByTeacherUCN(String teacherUCN) {
        Teacher teacher = teacherRepository.findTeacherByUCN(teacherUCN);
        return subjectRepository.findSubjectByTeacher_Id(teacher.getId());
    }

    public List<Subject> findSubjectsByStudentId(Long studentId) {

        return attendanceRepository
                .findAttendancesByStudentId(studentId)
                .stream()
                .map(attendance -> attendance.getSubject())
                .collect(Collectors.toList());
    }
}
