package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParentsMaticarRequest {

    private String parent_1_iden_number;
    private String parent_2_iden_number;

    public static ParentsMaticarRequest of(BirthCertificateRequest request) {
        return new ParentsMaticarRequest(request.getParent1Id(), request.getParent2Id());
    }
}
