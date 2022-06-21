package ftn.euprava.mupvozila.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String identityNumber;

    private List<String> roles;


}
