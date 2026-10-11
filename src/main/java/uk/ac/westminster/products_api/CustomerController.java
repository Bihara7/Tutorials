package uk.ac.westminster.products_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final List<Customer> customers = List.of(
            new Customer(
                    1L,
                    "Bihara",
                    new Address("Main Street", "Colombo", "00100")
            ),
            new Customer(
                    2L,
                    "Alex",
                    new Address("High Street", "London", "SW1A 1AA")
            )
    );

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customers;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}