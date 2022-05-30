package yu.rs.co.edfeahs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yu.rs.co.edfeahs.model.Mark;

@Repository
@Transactional
public interface MarkRepository extends JpaRepository<Mark, Long> {
}
