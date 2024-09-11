package machado.ramon.basiccrud.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import machado.ramon.basiccrud.model.Category;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Page<Category> findAll(PageRequest pageRequest) {
    return categoryRepository.findAll(pageRequest);
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
  public Category update(UUID id, Category category) throws RuntimeException{
    Category categoryToUpdate = findById(id);
    categoryToUpdate.setName(category.getName());
    return categoryRepository.save(categoryToUpdate);
  }

  private Optional<Category> findByName(String name) {
    return categoryRepository.findByName(name);
  }
}
