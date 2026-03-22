package co.prueba.accenture.backend.pruebaaccenturedev.service;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateStockProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductResponse;



public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse updateNameProduct(Long id, UpdateNameProductRequest request);
    ProductResponse upddateStockProduct(Long id, UpdateStockProductRequest request);
    void deleteProductBranch(Long branchId, Long productId);
}
