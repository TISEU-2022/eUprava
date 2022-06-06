package yu.rs.co.edfeahs.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoundStudentsDto {
    private Long id;
    private String UCN;
    private String firstName;
    private String lastName;
    private Double averageSubjectMark;
}
