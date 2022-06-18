package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.RegistrationCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationCertificateRepository extends JpaRepository<RegistrationCertificate, Long> {

    List<RegistrationCertificate> findAllByRequestTrue();

    Optional<RegistrationCertificate> findByUserId(String userId);

    List<RegistrationCertificate> findAllByUserId(String userId);
}
