package com.eUprava.eUprava.repository;
import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NosenjeRepository {

    Optional<Object> findOne(Long nosenjeId);

    List<ZahtevZaNosenje> findAll();

    ZahtevZaNosenje save(ZahtevZaNosenje zahtevZaNosenje);

    void deleteById(Long nosenjeId);

    Optional<Object> findById(Long nosenjeId);
}
