package machado.ramon.basiccrud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import machado.ramon.basiccrud.model.Category;
import machado.ramon.basiccrud.model.CategoryDTO;
import machado.ramon.basiccrud.service.CategoryService;

import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/category")
public class CategoryController {

  private CategoryService categoryService;

  CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/findAll")
  public ResponseEntity<Page<Category>> findAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
    return ResponseEntity.ok().body(categoryService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy))));
  }

  @GetMapping("/findById/{id}")
  public ResponseEntity<Category> findById(@PathVariable UUID id) {
    try {
      return ResponseEntity.ok().body(categoryService.findById(id));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/create")
  public ResponseEntity<Object> create(@RequestBody CategoryDTO categoryDto) {
    try{
      Category category = new Category();
      BeanUtils.copyProperties(categoryDto, category);
      return ResponseEntity.ok().body(categoryService.create(category));
    }catch(RuntimeException e){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }
  @PutMapping("update/{id}")
  public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Category category) {
    try{
      return ResponseEntity.ok().body(categoryService.update(id, category));
    }catch(RuntimeException e){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }

}
