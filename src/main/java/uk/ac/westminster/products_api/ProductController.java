package uk.ac.westminster.products_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final List<Product> products = List.of(
            new Product(1L, "Keyboard", 4500.00),
            new Product(2L, "Mouse", 2500.00),
            new Product(3L, "Monitor", 65000.00)
    );

    @GetMapping("/products")
    public List<Product> getProducts() {
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}