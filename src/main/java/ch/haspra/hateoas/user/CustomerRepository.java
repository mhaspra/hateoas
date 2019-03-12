package ch.haspra.hateoas.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomerRepository {

    private final HashMap<Long, Customer> customers;

    public CustomerRepository() {
        customers = new HashMap<>();

        Customer michi = new Customer(1, "Michi");
        Customer lena = new Customer(2, "Lena");

        customers.put(michi.getCustomerNumber(), michi);
        customers.put(lena.getCustomerNumber(), lena);
    }

    public List<Customer> getAll(){
        ArrayList<Customer> customers = new ArrayList<>();
        this.customers.values().forEach(customer -> customers.add(new Customer(customer)));
        return customers;
    }

    public Customer get(long customerNumber){
        return new Customer(customers.get(customerNumber));
    }
}
