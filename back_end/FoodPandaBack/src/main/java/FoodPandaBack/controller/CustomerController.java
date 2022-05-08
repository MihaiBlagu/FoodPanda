package FoodPandaBack.controller;

import FoodPandaBack.model.Customer;
import FoodPandaBack.service.CustomerService;
import FoodPandaBack.utils.EmailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        logger.info("Customer Controller: Received GET Request - get all customers");
        return customerService.getCustomers();
    }

    @PostMapping("/customers")
    public Customer newCustomer(@RequestBody Customer newCustomer) {
        logger.info("Customer Controller: Received POST Request - insert new customer");
        return customerService.saveCustomer(newCustomer);

    }

    @GetMapping("/customers/{username}")
    public Customer getCustomerByUsername(@PathVariable String username) {
        logger.info("Customer Controller: Received GET Request - get customer by username");
        return customerService.getCustomerByUsername(username);
    }

    @PostMapping("/customers/email")
    public int sendEmail(@RequestBody EmailInfo emailInfo) {
        logger.info("Customer Controller: Received Request - send email");
        return customerService.sendEmail(emailInfo.getAddress(),
                emailInfo.getDetails(),
                emailInfo.getCart(),
                emailInfo.getTotal());
    }
}