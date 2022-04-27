package ftn.euprava.zdravstvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IzvestajPregleda {

    private Termin termin;
    private String sadrzaj;
}
