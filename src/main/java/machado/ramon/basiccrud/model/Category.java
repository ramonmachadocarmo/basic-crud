package machado.ramon.basiccrud.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;

@Entity
public class Category implements Serializable {

  private static final long seriaVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(length = 100, nullable = false)
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Product> products;

  public List<Product> getProducts() {
    if (products != null) {
      products.forEach(product -> product.setCategory(null));
    }
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public static long getSeriaversionuid() {
    return seriaVersionUID;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
