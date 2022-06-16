package yu.rs.co.edfeahs.web.dto;

import lombok.Data;
import yu.rs.co.edfeahs.model.enums.Semester;

@Data
public class MarkSearchParam {

    private Long studentId;
    private Long subjectId;
    private Semester semester;

}
