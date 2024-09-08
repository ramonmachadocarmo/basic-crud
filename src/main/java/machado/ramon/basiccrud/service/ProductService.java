package machado.ramon.basiccrud.service;

import org.springframework.stereotype.Service;

import machado.ramon.basiccrud.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product save(Product product) {
    isValidProduct(product);

    return productRepository.save(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  private void isValidProduct(Product product) {
    if (product.getPrice() <= 0) {
      throw new IllegalArgumentException("Price must be greater than zero");
    }
    LocalDateTime now = LocalDate.now().atStartOfDay();
    if (product.getExpirationDate().isBefore(now)) {
      throw new IllegalArgumentException("Expiration date must be in the future");
    }
  }
}
