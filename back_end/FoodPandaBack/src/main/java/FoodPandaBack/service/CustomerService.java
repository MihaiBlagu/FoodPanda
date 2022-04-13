package FoodPandaBack.service;

import FoodPandaBack.model.Customer;
import FoodPandaBack.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer newCustomer) {
        //newCustomer.setPassword(encoder.encode(newCustomer.getPassword()));
        //encoder.matches(rawPass, pass in db)
        return customerRepository.save(newCustomer);
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
