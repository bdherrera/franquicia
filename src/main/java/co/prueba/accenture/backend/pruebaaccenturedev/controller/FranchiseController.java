package co.prueba.accenture.backend.pruebaaccenturedev.controller;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateFranchaiseRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameFranchiseRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.FranchiseResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.ProductMaxStockResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franquicias")
@RequiredArgsConstructor
@Tag(name = "Franquicias", description = "Endpoints para la gestión de franquicias")
public class FranchiseController {

    private final FranchiseService franchiseService;

    @Operation(summary = "Crear una nueva franquicia", description = "Registra una franquicia en el sistema.")
    @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente")
    @PostMapping
    public ResponseEntity<FranchiseResponse> createFranchise(@Valid @RequestBody CreateFranchaiseRequest request) {
        FranchiseResponse response = franchiseService.createFranchise(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Actualizar nombre de la franquicia", description = "Modifica el nombre de una franquicia existente.")
    @PatchMapping("/{franquiciaId}/nombre")
    public ResponseEntity<FranchiseResponse> updateFranchiseName(
            @Parameter(description = "ID de la franquicia") @PathVariable Long franquiciaId,
            @Valid @RequestBody UpdateNameFranchiseRequest request) {
        FranchiseResponse response = franchiseService.updateForName(franquiciaId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Producto con mayor stock por sucursal",
            description = "Retorna una lista de productos con el stock más alto para cada sucursal de una franquicia específica.")
    @GetMapping("/{franquiciaId}/productos/max-stock")
    public ResponseEntity<List<ProductMaxStockResponse>> getMaxStockForBranches(
            @Parameter(description = "ID de la franquicia") @PathVariable Long franquiciaId) {
        List<ProductMaxStockResponse> response = franchiseService.getProductMaxStock(franquiciaId);
        return ResponseEntity.ok(response);
    }
}