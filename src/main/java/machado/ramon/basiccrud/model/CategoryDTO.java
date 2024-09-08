package machado.ramon.basiccrud.model;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
    @NotBlank String name) {

}
