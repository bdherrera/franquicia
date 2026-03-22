package co.prueba.accenture.backend.pruebaaccenturedev.dto.response;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Long  branchId;
    private String branchName;
    private Integer stock ;
}
