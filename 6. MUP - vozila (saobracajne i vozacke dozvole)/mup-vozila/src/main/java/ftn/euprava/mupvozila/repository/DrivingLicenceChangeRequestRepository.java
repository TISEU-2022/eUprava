package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.DrivingLicenceChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingLicenceChangeRequestRepository extends JpaRepository<DrivingLicenceChangeRequest, Long> {
}
