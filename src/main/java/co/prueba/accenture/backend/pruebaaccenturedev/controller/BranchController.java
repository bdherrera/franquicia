package co.prueba.accenture.backend.pruebaaccenturedev.controller;

import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.CreateBranchRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.request.UpdateNameBranchRequest;
import co.prueba.accenture.backend.pruebaaccenturedev.dto.response.BranchResponse;
import co.prueba.accenture.backend.pruebaaccenturedev.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
@Tag(name = "Sucursales", description = "Endpoints para la gestión de sucursales")
public class BranchController {

    private final BranchService branchService;

    @Operation(summary = "Agregar sucursal a una franquicia")
    @PostMapping
    public ResponseEntity<BranchResponse> createBranch(@Valid @RequestBody CreateBranchRequest request) {
        BranchResponse branchResponse = branchService.createBranch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(branchResponse);
    }

    @Operation(summary = "Actualizar nombre de la sucursal")
    @PatchMapping("/{branchId}/name")
    public ResponseEntity<BranchResponse> updateBranchName(
            @PathVariable Long branchId,
            @Valid @RequestBody UpdateNameBranchRequest request) {
        BranchResponse response = branchService.updateNameBranch(branchId, request);
        return ResponseEntity.ok(response);
    }
}
