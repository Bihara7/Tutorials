package uk.ac.westminster.products_api.repository;

import org.springframework.stereotype.Repository;
import uk.ac.westminster.products_api.DigitalProduct;
import uk.ac.westminster.products_api.PhysicalProduct;
import uk.ac.westminster.products_api.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private Long nextId = 1L;

    // Constructor: add sample products
    public InMemoryProductRepository() {

        save(new DigitalProduct(
                null,
                "Clean Code (E-book)",
                29.99,
                "/downloads/clean-code.pdf",
                4.2
        ));

        save(new PhysicalProduct(
                null,
                "27-inch Monitor",
                249.99,
                5.4,
                "61cm x 41cm x 18cm"
        ));
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
