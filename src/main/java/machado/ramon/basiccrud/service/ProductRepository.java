package machado.ramon.basiccrud.service;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import machado.ramon.basiccrud.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

  List<Product> findByNameContainingIgnoreCase(String name);
  List<Product> findByDescriptionContainingIgnoreCase(String description);
}
