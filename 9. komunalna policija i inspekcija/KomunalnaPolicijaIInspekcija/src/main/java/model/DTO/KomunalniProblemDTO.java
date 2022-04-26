package model.DTO;

import lombok.Data;
import model.Podnosilac;
import model.VrstaKomunalnogProblema;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class KomunalniProblemDTO {

    private UUID id;
    private String opis;
    private Podnosilac podnosilac;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumPodnosenja;
    private Date datumDogadjaja;
    private VrstaKomunalnogProblema vrstaKomunalnogProblema;
    private List<String> putanjeDoDatoteka;
}
