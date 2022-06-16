package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UverenjeZahtevDTO {

    private String name;
    private String prezime;
    private String jmbg;
    private String obrazlozenje;
}
