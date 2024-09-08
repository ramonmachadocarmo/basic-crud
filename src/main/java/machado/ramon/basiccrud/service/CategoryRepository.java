package machado.ramon.basiccrud.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import machado.ramon.basiccrud.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

  Optional<Category> findByName(String name);

}
