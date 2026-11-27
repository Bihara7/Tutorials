package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;
import uk.ac.westminster.products_api.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Add a product
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    // Delete product by ID
    public void deleteProductById(Long id) {
        getProductById(id); // throws 404 if it is not there
        repository.delete(id);
    }

    // Find products by name
    public List<Product> findByName(String name) {
        return repository.findAll().stream()
                .filter(product -> product.getName().contains(name))
                .toList();
    }
    public List<String> getAllSummaries() {

        List<String> summaries = new ArrayList<>();

        for (Product p : repository.findAll()) {

            summaries.add(p.getSummary());
        }

        return summaries;
    }
}
