package yu.rs.co.edfeahs.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import yu.rs.co.edfeahs.model.enums.Semester;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateMarkDto {

    private Long subjectId;
    private Long studentId;
    private Integer value;
    private Semester semester;

}
