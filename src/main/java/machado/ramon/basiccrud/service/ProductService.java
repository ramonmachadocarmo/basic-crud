package machado.ramon.basiccrud.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import machado.ramon.basiccrud.model.Category;
import machado.ramon.basiccrud.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryService categoryService;

  ProductService(ProductRepository productRepository, CategoryService categoryService) {
    this.productRepository = productRepository;
    this.categoryService = categoryService;
  }

  public Product create(Product product, UUID categoryId) {
    Category category = categoryService.findById(categoryId);
    isValidProduct(product);

    product.setCategory(category);

    return productRepository.save(product);
  }

  public Product update(Product product, UUID id) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (productOptional.isEmpty())
      throw new RuntimeException("Product not found");

    isValidProduct(product);

    BeanUtils.copyProperties(product, productOptional.get(), "id");

    return productRepository.save(product);
  }

  public void delete(UUID id) {
    Product product = findById(id);
    productRepository.delete(product);
  }

  public void deleteAll() {
    productRepository.deleteAll();
    ;
  }

  public Page<Product> findAll(PageRequest pageRequest) {
    return productRepository.findAll(pageRequest);
  }

  public Product findById(UUID id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isEmpty())
      throw new RuntimeException("Product not found");

    return product.get();
  }

  private List<Product> findByName(String name) {
    return productRepository.findByName(name);
  }

  public Page<Product> findByName(String name, PageRequest pageRequest) {
    return productRepository.findByNameContainingIgnoreCase(name, pageRequest);
  }

  public Page<Product> findByDescription(String description, PageRequest pageRequest) {
    return productRepository.findByDescriptionContainingIgnoreCase(description, pageRequest);
  }

  private void isValidProduct(Product product) {
    if (findByName(product.getName()).size() > 0) {
      throw new IllegalArgumentException("Product already exists");
    }
    if (product.getPrice() <= 0) {
      throw new IllegalArgumentException("Price must be greater than zero");
    }
    LocalDateTime now = LocalDate.now().atStartOfDay();
    if (product.getExpirationDate().isBefore(now)) {
      throw new IllegalArgumentException("Expiration date must be in the future");
    }
  }
}
