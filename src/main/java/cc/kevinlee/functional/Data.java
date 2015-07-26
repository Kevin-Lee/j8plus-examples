package cc.kevinlee.functional;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-07-26
 */
public class Data {

  public static BigDecimal $(String value) {
    return new BigDecimal(value);
  }

  public static class Product {
    private Long id;
    private String name;
    private BigDecimal price;

    public static Product product(final Long id, final String name, final BigDecimal price) {
      return new Product(id, name, price);
    }


    public Product(final Long id, final String name, final BigDecimal price) {
      this.id = id;
      this.name = name;
      this.price = price;
    }

    public Long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public void setName(final String name) {
      this.name = name;
    }

    public BigDecimal getPrice() {
      return price;
    }

    public void setPrice(final BigDecimal price) {
      this.price = price;
    }

    @Override
    public String toString() {
      return new StringBuilder("Product{")
          .append("id=").append(id)
          .append(", name='").append(name).append('\'')
          .append(", price=").append(price)
          .append('}')
          .toString();
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, name);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof Product)) {
        return false;
      }
      final Product that = (Product) obj;
      return Objects.equals(this.name, that.getName());
    }
  }
}
