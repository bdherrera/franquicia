package co.prueba.accenture.backend.pruebaaccenturedev.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBranchRequest {

    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String name;

    @NotNull(message = "El id de la franquicia es obligatorio")
    private Long franchiseId;
}
