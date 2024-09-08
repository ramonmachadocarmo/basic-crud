package machado.ramon.basiccrud.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import machado.ramon.basiccrud.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
