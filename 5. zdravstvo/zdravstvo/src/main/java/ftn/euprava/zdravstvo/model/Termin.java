package ftn.euprava.zdravstvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Termin {

    private Integer doktorJMBG;
    private Integer gradjaninJMBG;
    private LocalDate vreme;
    private StatusTermina statusTermina;
}