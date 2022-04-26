package model;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class KomunalniProblem {

    private UUID id;
    private String opis;
    private Podnosilac podnosilac;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumPodnosenja;
    private Date datumDogadjaja;
    private VrstaKomunalnogProblema vrstaKomunalnogProblema;
    private List<String> putanjeDoDatoteka;

    @Override
    public String toString() {
        return "KomunalniProblem{" +
                "id=" + id +
                ", opis='" + opis + '\'' +
                ", podnosilac=" + podnosilac +
                ", adresaDogadjaja='" + adresaDogadjaja + '\'' +
                ", mestoDogadjaja='" + mestoDogadjaja + '\'' +
                ", datumPodnosenja=" + datumPodnosenja +
                ", datumDogadjaja=" + datumDogadjaja +
                ", vrstaKomunalnogProblema=" + vrstaKomunalnogProblema +
                ", putanjeDoDatoteka=" + putanjeDoDatoteka +
                '}';
    }
}
