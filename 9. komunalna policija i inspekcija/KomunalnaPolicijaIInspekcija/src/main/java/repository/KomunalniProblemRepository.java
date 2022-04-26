package repository;

import model.KomunalniProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KomunalniProblemRepository extends JpaRepository<KomunalniProblem, UUID> {
}
