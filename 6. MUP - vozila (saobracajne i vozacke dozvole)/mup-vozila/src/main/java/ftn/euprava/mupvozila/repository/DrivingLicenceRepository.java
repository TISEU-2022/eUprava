package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.DrivingLicence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrivingLicenceRepository extends JpaRepository<DrivingLicence, Long> {

    Optional<DrivingLicence> findByUser_IdentityNumber(String identityNumber);
}
