package FoodPandaBack.service;

import FoodPandaBack.model.Customer;
import FoodPandaBack.model.Order;
import FoodPandaBack.model.Restaurant;
import FoodPandaBack.repository.OrderRepository;
import FoodPandaBack.utils.OrderStatus;
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
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllOrdersSuccess() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.add(new Order());

        when(orderRepository.findAll()).thenReturn(orderList);

        List<Order> controlList = orderService.getOrders();

        assertNotNull(controlList);
    }

    @Test
    public void saveNewOrderSuccess() {
        Order successOrder = new Order(1L, OrderStatus.PENDING, new Customer(), new Restaurant());

        when(orderRepository.save(successOrder)).thenReturn(successOrder);

        Order controlOrder = orderService.saveOrder(successOrder);

        assertEquals(successOrder, controlOrder);
    }

    @Test(expected = Exception.class)
    public void saveNewOrderFail() {
        when(orderService.saveOrder(null)).thenThrow(new Exception());

        Order controlOrder = orderService.saveOrder(null);

        assertNull(controlOrder);
    }

    @Test
    public void getOrdersByCustomerIdSuccess() {
        Customer customer = new Customer(
                1L,
                "newCustomer",
                "1234",
                new HashSet<>(),
                new HashSet<>());

        Order o1 = new Order(1L, OrderStatus.PENDING, customer, new Restaurant());
        Order o2 = new Order(2L, OrderStatus.PENDING, customer, new Restaurant());
        Order o3 = new Order(3L, OrderStatus.PENDING, customer, new Restaurant());

        List<Order> orderList = new ArrayList<>();
        orderList.add(o1);
        orderList.add(o2);
        orderList.add(o3);

        when(orderRepository.getOrderByCustomerId(customer.getUserId())).thenReturn(orderList);

        List<Order> controlList = orderService.getOrdersByCustomerId(customer.getUserId());

        assertEquals(orderList, controlList);
    }

    @Test
    public void getOrdersByCustomerIdFail() {
        when(orderRepository.getOrderByCustomerId(null)).thenReturn(null);

        List<Order> controlList = orderService.getOrdersByCustomerId(null);

        assertNull(controlList);
    }

    @Test
    public void filterOrdersByStatusSuccess() {
        ArrayList<Order> orders = new ArrayList<>();
        Order o1 = new Order();
        Order o2 = new Order();
        Order o3 = new Order();
        o1.setOrderStatus(OrderStatus.DELIVERED);
        o2.setOrderStatus(OrderStatus.DELIVERED);
        o3.setOrderStatus(OrderStatus.PENDING);
        orders.add(o1);
        orders.add(o2);
        orders.add(o3);

        when(orderRepository.getOrdersByRestId(7L)).thenReturn(orders);

        List<Order> filteredOrders = orderService.getFilteredOrdersByStatus(7L, OrderStatus.DELIVERED);

        for(Order o : filteredOrders) {
            assertNotNull(o);
            assertEquals(OrderStatus.DELIVERED, o.getOrderStatus());
        }
        assertEquals(2, filteredOrders.size());
    }

    @Test
    public void filterOrdersByStatusFail() {
        when(orderRepository.getOrdersByRestId(null)).thenReturn(null);

        List<Order> controlIList = orderService.getFilteredOrdersByStatus(null, OrderStatus.PENDING);

        assertNull(controlIList);
    }
}
