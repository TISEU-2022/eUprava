package yu.rs.co.edfeahs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yu.rs.co.edfeahs.model.Student;
import yu.rs.co.edfeahs.web.dto.StudentSearchParam;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //:#{#methodParamAlias.objectField} - Used to acces the param from the method signature
    // Query finds the students with firstName + lastName that is LIKE studentSarchParam.studentName,
    // and student grade = studentSearchParam.grade but only for the certain subjectId
    @Query("SELECT s FROM Attendance a LEFT OUTER JOIN Student s ON a.student = s WHERE " +
            "(:#{#params.studentName} IS NULL OR CONCAT(s.firstName, ' ', s.lastName) LIKE %:#{#params.studentName}%) AND " +
            "(:#{#params.grade} IS NULL OR s.grade = :#{#params.grade}) AND " +
            "(:#{#params.subjectId} IS NULL OR a.subject.id = :#{#params.subjectId})")
    List<Student> findStudents(@Param("params") StudentSearchParam studentSearchParam);

    @Query("SELECT s FROM Student s WHERE s.firstName LIKE CONCAT('%', :firstName, '%')" )
    List<Student> findStudentByName(@Param("firstName") String firstName);

}
