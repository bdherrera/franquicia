package co.prueba.accenture.backend.pruebaaccenturedev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponse {
    private long id;
    private String name;
    private Long franchiseId;
    private String franchiseName;

    @Builder.Default
    private List<ProductResponse> products = new ArrayList<>();
}
