package ch.haspra.hateoas.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class Customer extends ResourceSupport {
    private final long customerNumber;
    private String name;

    @JsonCreator
    public Customer(@JsonProperty("customerNumber") long customerNumber, @JsonProperty("name") String name) {
        this.customerNumber = customerNumber;
        this.name = name;
    }

    public Customer(Customer customer) {
        customerNumber = customer.getCustomerNumber();
        name = customer.getName();
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public String getName() {
        return name;
    }
}
