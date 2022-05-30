package yu.rs.co.edfeahs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import yu.rs.co.edfeahs.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}