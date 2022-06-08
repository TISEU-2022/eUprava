package com.eUprava.eUprava.repository;

import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NabavkaRepository {


    Optional<Object> findOne(Long nabavkaId);

    List<ZahtevZaNabavku> findAll();

    ZahtevZaNabavku save(ZahtevZaNabavku zahtevZaNabavku);

    void deleteById(Long id);

    Optional<Object> findById(Long id);
}
