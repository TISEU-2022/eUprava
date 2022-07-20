package yu.rs.co.edfeahs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yu.rs.co.edfeahs.model.Attendance;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findAttendanceByStudentIdAndSubjectId(Long studentId, Long subjectId);

    List<Attendance> findAttendancesByStudentId(Long studentId);

}