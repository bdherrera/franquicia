package co.prueba.accenture.backend.pruebaaccenturedev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaxStockResponse {

    private Long franchiseId;
    private String franchiseName;
    private Long branchId;
    private String branchName;
    private Long productId;
    private String productName;
    private Integer productStock;

}
