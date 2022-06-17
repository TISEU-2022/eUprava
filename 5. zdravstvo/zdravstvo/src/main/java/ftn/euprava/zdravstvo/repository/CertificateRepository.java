package ftn.euprava.zdravstvo.repository;

import ftn.euprava.zdravstvo.model.Certificate;
import ftn.euprava.zdravstvo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Query(value = "select * from certificate where user_jmbg = ?1",
            nativeQuery = true)
    List<Certificate> findByUserJmbg(String jmbg);

}