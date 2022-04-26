package ftn.euprava.mupvozila.web.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String id;

    private String identityNumber;

    private String firstName;

    private String lastName;

    private String username;

    private String[] roles;

}
