package machado.ramon.basiccrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import machado.ramon.basiccrud.model.Category;
import machado.ramon.basiccrud.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

  private CategoryService categoryService;

  CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/findAll")
  public ResponseEntity<List<Category>> findAll() {
    return ResponseEntity.ok().body(categoryService.findAll());
  }
}
