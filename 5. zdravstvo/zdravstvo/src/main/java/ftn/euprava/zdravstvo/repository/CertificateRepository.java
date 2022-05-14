package ftn.euprava.zdravstvo.repository;

import ftn.euprava.zdravstvo.model.Certificate;
import ftn.euprava.zdravstvo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByUser(User user);
}