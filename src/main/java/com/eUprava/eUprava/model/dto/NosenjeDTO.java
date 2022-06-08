package com.eUprava.eUprava.model.dto;
import com.eUprava.eUprava.model.entity.OruzniList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NosenjeDTO implements Serializable {
    private Long nosenje_id;
    private Boolean vazecaLicna;
    private Boolean sudskoUverenje;
    private OruzniList list;
}
