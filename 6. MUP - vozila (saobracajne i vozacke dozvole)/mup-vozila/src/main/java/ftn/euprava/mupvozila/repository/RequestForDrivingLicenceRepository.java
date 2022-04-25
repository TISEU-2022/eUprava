package ftn.euprava.mupvozila.repository;

import ftn.euprava.mupvozila.model.RequestForDrivingLicence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestForDrivingLicenceRepository extends JpaRepository<RequestForDrivingLicence, Long> {
}
