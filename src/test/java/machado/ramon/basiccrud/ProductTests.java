package machado.ramon.basiccrud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import machado.ramon.basiccrud.model.Category;
import machado.ramon.basiccrud.model.Product;
import machado.ramon.basiccrud.service.CategoryService;
import machado.ramon.basiccrud.service.ProductService;

@SpringBootTest
public class ProductTests {

  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;

  private Category category;

  @BeforeEach
  public void setup() {
    category = new Category();
    category.setName("Teste");
    List<Category> list = categoryService.findAll();
    if(list.isEmpty()){
      category = categoryService.create(category);
    }else{
      category = list.get(0);
    }
  }

  private Product productNOkPrice() {
    Product product = new Product();
    product.setName("Notebook");
    product.setDescription("Teste notebook");
    product.setPrice(0.0);
    product.setExpirationDate(LocalDateTime.now().plusDays(100));
    product.setFilename("caminho do arquivo");

    return product;
  }

  private Product productNOkExpirationDate() {
    Product product = new Product();
    product.setName("Notebook");
    product.setDescription("Teste notebook");
    product.setPrice(10.0);
    product.setExpirationDate(LocalDateTime.now().minusYears(100));
    product.setFilename("caminho do arquivo");

    return product;
  }

  private Product productOk() {
    Product product = new Product();
    product.setName("Notebook");
    product.setDescription("Teste notebook");
    product.setPrice(100.50);
    product.setExpirationDate(LocalDateTime.now().plusDays(100));
    product.setFilename("caminho do arquivo");

    return product;
  }

  @Test
  public void shouldReturnPriceError() {
    Product product = productNOkPrice();
    Exception exception = assertThrows(Exception.class, () -> productService.create(product, category.getId()));

    assertEquals(exception.getMessage(), "Price must be greater than zero");
  }

  @Test
  public void shouldReturnExpirationError() {
    Product product = productNOkExpirationDate();
    Exception exception = assertThrows(Exception.class, () -> productService.create(product, category.getId()));

    assertEquals(exception.getMessage(), "Expiration date must be in the future");
  }
  @Test
  public void shouldCreateProduct() {
    Product product = productOk();
    Product productCreated = productService.create(product, category.getId());

    assertEquals(productCreated.getName(), product.getName());
    assertEquals(productCreated.getDescription(), product.getDescription());
    assertEquals(productCreated.getPrice(), product.getPrice());
    assertEquals(productCreated.getExpirationDate(), product.getExpirationDate());
    assertEquals(productCreated.getFilename(), product.getFilename());
  }
}
