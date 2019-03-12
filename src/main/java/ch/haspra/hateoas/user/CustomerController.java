package ch.haspra.hateoas.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CustomerController {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(path = "/customers", produces = MediaTypes.HAL_JSON_VALUE)
    public Resources<Customer> customers() {

        List<Customer> customers = customerRepository.getAll();
        customers.forEach(customer -> customer.add(linkTo(methodOn(CustomerController.class).customers(customer.getCustomerNumber())).withSelfRel()));

        return new Resources<>(customers, linkTo(methodOn(CustomerController.class).customers()).withSelfRel());
    }

    @RequestMapping(path = "/customers/{customerNumber}", produces = MediaTypes.HAL_JSON_VALUE)
    public Resource<Customer> customers(@PathVariable("customerNumber") long customerNumber) {

        Customer customer = customerRepository.get(customerNumber);
        customer.add(linkTo(methodOn(CustomerController.class).customers(customer.getCustomerNumber())).withSelfRel());

        return new Resource<>(customer);
    }
}
