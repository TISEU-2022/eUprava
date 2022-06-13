package com.eUprava.eUprava.repository;

import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface NabavkaRepository extends JpaRepository<ZahtevZaNabavku, Long> {

    //Optional<ZahtevZaNabavku> findOne(Long nabavkaId);

    ZahtevZaNabavku save(ZahtevZaNabavku zahtevZaNabavku);

    void deleteById(Long id);

    Optional<ZahtevZaNabavku> findById(Long id);
}
