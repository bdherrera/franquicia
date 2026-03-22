package co.prueba.accenture.backend.pruebaaccenturedev.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNameProductRequest {
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;
}
