package machado.ramon.basiccrud.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductDTO(
    String name,
    String description,
    Double price,
    LocalDateTime expirationDate,
    String filename,
    UUID category) {

}
