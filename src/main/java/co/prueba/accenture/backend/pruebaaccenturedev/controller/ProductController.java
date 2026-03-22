package co.prueba.accenture.backend.pruebaaccenturedev.controller;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateStockProductRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Endpoints para la gestión de productos y stock")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Agregar nuevo producto a una sucursal")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse productResponse = productService.createProduct(request);
        return ResponseEntity.ok(productResponse);
    }

    @Operation(summary = "Actualizar nombre de un producto")
    @PatchMapping("/{productId}/nombre")
    public ResponseEntity<ProductResponse> updateProductName(
            @PathVariable Long productId, @Valid @RequestBody UpdateNameProductRequest request) {
        ProductResponse response = productService.updateNameProduct(productId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Modificar el stock de un producto")
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<ProductResponse> updateProductStockResponse(
            @PathVariable Long productId, @Valid @RequestBody UpdateStockProductRequest request) {
        ProductResponse response = productService.upddateStockProduct(productId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar un producto de una sucursal")
    @DeleteMapping("/sucursales/{branchId}/productos/{productId}")
    public ResponseEntity<Void> deleteProductOfBranch(
            @PathVariable Long branchId,
            @PathVariable Long productId) {
        productService.deleteProductBranch(branchId, productId);
        return ResponseEntity.noContent().build();
    }
}