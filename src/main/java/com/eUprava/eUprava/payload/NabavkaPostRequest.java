package com.eUprava.eUprava.payload;

import com.eUprava.eUprava.model.entity.Oruzije;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class NabavkaPostRequest {

    @NotBlank
    private Boolean vazecaLicna;

    @NotBlank
    private Boolean sudskoUverenje;

    @NotBlank
    @Size(min = 3,max = 300)
    private String kolicina;

    @NotBlank
    private Boolean lekarskoUverenje;

    @NotBlank
    private Oruzije oruzije;

}
