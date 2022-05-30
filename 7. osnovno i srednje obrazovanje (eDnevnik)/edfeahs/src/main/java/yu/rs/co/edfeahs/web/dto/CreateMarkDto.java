package yu.rs.co.edfeahs.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateMarkDto {

    private Long attendanceId;
    private Integer value;
    private String semester;

}
