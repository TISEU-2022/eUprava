package com.eUprava.eUprava.repository;
import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NosenjeRepository extends JpaRepository<ZahtevZaNosenje, Long> {

    //Optional<ZahtevZaNosenje> findOne(Long nosenjeId);

    ZahtevZaNosenje save(ZahtevZaNosenje zahtevZaNosenje);

    void deleteById(Long nosenjeId);

    Optional<ZahtevZaNosenje> findById(Long nosenjeId);
}
