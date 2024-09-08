package machado.ramon.basiccrud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import machado.ramon.basiccrud.model.Product;
import machado.ramon.basiccrud.model.ProductDTO;
import machado.ramon.basiccrud.service.ProductService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/findAll")
  public ResponseEntity<List<Product>> findAll() {
    return ResponseEntity.ok().body(productService.findAll());
  }

  @GetMapping("/findById/{id}")
  public ResponseEntity<Object> findById(@PathVariable UUID id) {
    try {
      return ResponseEntity.ok().body(productService.findById(id));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/findByName/{name}")
  public ResponseEntity<List<Product>> findByName(@PathVariable String name) {
    return ResponseEntity.ok().body(productService.findByName(name));
  }

  @GetMapping("/findByDescription/{description}")
  public ResponseEntity<List<Product>> findByDescription(@PathVariable String description) {
    return ResponseEntity.ok().body(productService.findByDescription(description));
  }

  @PostMapping("/create")
  public ResponseEntity<Object> postMethodName(@RequestBody ProductDTO productDTO) {
    try {
      Product product = new Product();
      BeanUtils.copyProperties(productDTO, product);
      return ResponseEntity.ok().body(productService.create(product, productDTO.category()));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Object> putMethodName(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {
    try {
      Product product = new Product();
      BeanUtils.copyProperties(productDTO, product);
      return ResponseEntity.ok().body(productService.update(product, id));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) {
    try {
      productService.delete(id);
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }
  @DeleteMapping("/deleteAll")
  public ResponseEntity<Object> deleteAll() {
    productService.deleteAll();
    return ResponseEntity.ok().build();
  }

}
