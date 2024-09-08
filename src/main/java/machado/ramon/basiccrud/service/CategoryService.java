package machado.ramon.basiccrud.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import machado.ramon.basiccrud.model.Category;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findById(UUID id) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isEmpty()) {
      throw new RuntimeException("Category not found");
    }
    return category.get();
  }
  public Category create(Category category) {
    if(findByName(category.getName()).isPresent()) {
      throw new RuntimeException("Category already exists");
    }
    return categoryRepository.save(category);
  }
  public Category update(UUID id, Category category) {
    Category categoryToUpdate = findById(id);
    categoryToUpdate.setName(category.getName());
    return categoryRepository.save(categoryToUpdate);
  }

  private Optional<Category> findByName(String name) {
    return categoryRepository.findByName(name);
  }
}
