package com.eUprava.eUprava.service;
import com.eUprava.eUprava.model.dto.NosenjeDTO;
import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import com.eUprava.eUprava.payload.NosenjePostRequest;
import java.util.List;
public interface NosenjeService {
    ZahtevZaNosenje findById(Long nosenjeId);
    List<ZahtevZaNosenje> findAll();
    ZahtevZaNosenje save(String token, NosenjeDTO nosenjeDTO)throws Exception;
    void remove (Long nosenje_id);
    ZahtevZaNosenje update(Long nosenje_id, NosenjePostRequest nosenjePostRequest);
}
