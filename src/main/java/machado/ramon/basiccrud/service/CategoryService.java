package machado.ramon.basiccrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import machado.ramon.basiccrud.model.Category;
import machado.ramon.basiccrud.repository.CategoryRepository;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }
}
