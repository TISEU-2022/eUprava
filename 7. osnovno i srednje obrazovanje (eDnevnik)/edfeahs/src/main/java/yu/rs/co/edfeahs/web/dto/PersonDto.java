package yu.rs.co.edfeahs.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

//    @JsonProperty("birth_certificate")
//    private BirthCertificateDto birthCertificateDto;
//
//    @JsonProperty("parents")
//    private String[] parents;

    @JsonProperty("children")
    private List<String> children;


}
