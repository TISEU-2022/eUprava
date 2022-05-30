package yu.rs.co.edfeahs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yu.rs.co.edfeahs.model.GradeAverage;

@Repository
public interface GradeAverageRepository extends JpaRepository<GradeAverage, Long> {
}
