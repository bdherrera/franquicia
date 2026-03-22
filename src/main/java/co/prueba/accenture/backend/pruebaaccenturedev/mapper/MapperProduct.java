package co.prueba.accenture.backend.pruebaaccenturedev.mapper;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductMaxStockResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Branch;
import co.prueba.accenture.backend.pruebaaccenturedev.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperProduct {

    public Product toEntity(CreateProductRequest request) {

        if (request == null) {
            return null;
        }

       Product product =new Product();

        product.setName(request.getName());
        product.setStock(request.getStock());

        return product;
    }
    public ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .branchId(product.getBranch() != null ? product.getBranch().getId() : null)
                .branchName(product.getBranch() != null ? product.getBranch().getName() : null)
                .build();
    }

    public ProductMaxStockResponse toResponseMaxStock(Product product) {
        if (product == null) {
            return null;
        }

        Branch branch = product.getBranch();

        return ProductMaxStockResponse.builder()
                .franchiseId(
                        branch != null && branch.getFranchise() != null
                                ? branch.getFranchise().getId()
                                : null
                )
                .branchName(
                        branch != null && branch.getFranchise() != null
                        ? branch.getFranchise().getName()
                                :null
                )
                .branchId(branch != null ? branch.getId() : null)
                .franchiseName(branch != null ? branch.getName() : null)
                .productId(product.getId())
                .productStock(product.getStock())
                .build();
    }
}
