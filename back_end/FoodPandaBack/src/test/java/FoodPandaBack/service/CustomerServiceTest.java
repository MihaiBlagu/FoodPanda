package FoodPandaBack.service;

import FoodPandaBack.model.Customer;
import FoodPandaBack.model.MenuItem;
import FoodPandaBack.model.Order;
import FoodPandaBack.repository.CustomerRepository;
import FoodPandaBack.utils.CartItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomersTest() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer());
        customerList.add(new Customer());
        customerList.add(new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> controlList = customerService.getCustomers();

        assertNotNull(controlList);
    }

    @Test
    public void getCustomerByUsernameSuccess() {
        Customer customerInDatabase = new Customer();
        customerInDatabase.setUsername("customerInDatabase");

        when(customerRepository.findByUsername("customerInDatabase")).thenReturn(customerInDatabase);

        Customer controlCustomer = customerService.getCustomerByUsername("customerInDatabase");

        assertEquals("customerInDatabase", controlCustomer.getUsername());
    }

    @Test
    public void getCustomerByUsernameFail() {
        when(customerService.getCustomerByUsername("customerNotInDatabase")).thenReturn(null);

        Customer controlCustomer = customerService.getCustomerByUsername("customerNotInDatabase");

        assertNull(controlCustomer);
    }

    @Test(expected = Exception.class)
    public void getNullCustomer() {
        when(customerService.getCustomerByUsername(null)).thenThrow(new Exception());

        Customer controlCustomer = customerService.getCustomerByUsername(null);

        assertNull(controlCustomer);
    }

    @Test
    public void saveNewCustomerSuccess() {
        Customer successCustomer = new Customer(1L,
                "successCustomer1",
                "123",
                new HashSet<Order>(),
                new HashSet<MenuItem>());

        Customer testCustomer = new Customer(1L,
                "successCustomer1",
                "123",
                new HashSet<Order>(),
                new HashSet<MenuItem>());

        when(customerRepository.save(testCustomer)).thenReturn(successCustomer);

        Customer controlCustomer = customerService.saveCustomer(testCustomer);

        assertEquals(successCustomer, controlCustomer);
    }

    @Test(expected = Exception.class)
    public void saveNewCustomerFail() {
        when(customerService.saveCustomer(null)).thenThrow(new Exception());

        Customer controlCustomer = customerService.saveCustomer(null);

        assertNull(controlCustomer);
    }

    @Test
    public void sendEmailSuccess() {
        String address = "address";
        String details = "details";
        List<CartItem> cart = new ArrayList<CartItem>();
        cart.add(new CartItem(new MenuItem(), 2));
        cart.add(new CartItem(new MenuItem(), 2));
        cart.add(new CartItem(new MenuItem(), 2));
        double total = 10.0;

        int controlStatus = customerService.sendEmail(address, details, cart, total);

        assertEquals(0, controlStatus);
    }

}
