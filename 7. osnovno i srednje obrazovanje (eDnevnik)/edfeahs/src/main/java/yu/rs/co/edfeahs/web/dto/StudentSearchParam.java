package yu.rs.co.edfeahs.web.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class StudentSearchParam {

    @Min(value = 1, message = "Grade must be >= 1")
    @Max(value = 8, message = "Grade must be <= 8")
    private Integer grade;

    private String studentName;

    private Long subjectId;

}
