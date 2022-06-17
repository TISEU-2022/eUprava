package ftn.euprava.zdravstvo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParentsRequest {
    @NotNull
    @NotEmpty
    @Size(min=13, max=13)
    private String parent1Id;
    @NotNull
    @NotEmpty
    @Size(min=13, max=13)
    private String parent2Id;
}
