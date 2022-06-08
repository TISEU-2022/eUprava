package com.eUprava.eUprava.payload;
import com.eUprava.eUprava.model.entity.OruzniList;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
public class NosenjePostRequest {
    @NotBlank
    private Boolean vazecaLicna;

    @NotBlank
    private Boolean sudskoUverenje;

    @NotBlank
    private OruzniList list;

}


