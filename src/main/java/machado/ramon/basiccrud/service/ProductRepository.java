package machado.ramon.basiccrud.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import machado.ramon.basiccrud.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

  Page<Product> findByNameContainingIgnoreCase(String name, PageRequest pageRequest);

  Page<Product> findByDescriptionContainingIgnoreCase(String description, PageRequest pageRequest);

  List<Product> findByName(String name);
}
