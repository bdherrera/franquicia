package co.prueba.accenture.backend.pruebaaccenturedev.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNameFranchiseRequest {
    @NotBlank(message = "El nombre de la franquicia es obligatorio")
    private String name;
}
