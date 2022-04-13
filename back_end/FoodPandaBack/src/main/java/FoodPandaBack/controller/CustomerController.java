package FoodPandaBack.controller;

import FoodPandaBack.model.Customer;
import FoodPandaBack.model.User;
import FoodPandaBack.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping("/customers")
    public Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerService.saveCustomer(newCustomer);
    }

    @GetMapping("/customers/{username}")
    public Customer getCustomerByUsername(@PathVariable String username){
        return customerService.getCustomerByUsername(username);
    }
}
