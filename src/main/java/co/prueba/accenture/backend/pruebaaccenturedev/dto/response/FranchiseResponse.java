package co.prueba.accenture.backend.pruebaaccenturedev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseResponse {
    private long id;
    private String name;
    List<BranchResponse> branches;
}
