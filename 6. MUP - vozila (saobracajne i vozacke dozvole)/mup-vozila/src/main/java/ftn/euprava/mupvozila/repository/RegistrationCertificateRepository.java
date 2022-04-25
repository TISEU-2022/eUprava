package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.RegistrationCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationCertificateRepository extends JpaRepository<RegistrationCertificate, Long> {
}
