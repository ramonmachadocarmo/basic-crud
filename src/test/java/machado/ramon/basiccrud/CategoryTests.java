package machado.ramon.basiccrud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import machado.ramon.basiccrud.model.Category;
import machado.ramon.basiccrud.service.CategoryService;

@SpringBootTest
public class CategoryTests {

  @Autowired
  private CategoryService categoryService;

  private Category categoryNOk() {
    return new Category();
  }

  private Category categoryOk() {
    Category category = new Category();
    category.setName("Teste Ok");
    return category;
  }

  @Test
  public void shouldCreateCategory() {
    Category category = categoryOk();
    Category newCategory = categoryService.create(category);

    assertEquals(category, newCategory);
  }

  @Test
  public void shouldReturnError() {
    Category category = categoryNOk();

    assertThrows(RuntimeException.class, () -> {
      categoryService.create(category);
    });

  }

}
