package ftn.euprava.zdravstvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicinskiKarton {

    private Integer gradjaninJMBG;
    private List<IzvestajPregleda> izvestajPregledaList = new ArrayList<>();

}
