package FoodPandaBack.service;

import FoodPandaBack.model.*;
import FoodPandaBack.repository.OrderMenuItemRepository;
import FoodPandaBack.utils.Category;
import FoodPandaBack.utils.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class OrderMenuItemServiceTest {

    @InjectMocks
    private OrderMenuItemService orderMenuItemService;

    @Mock
    OrderMenuItemRepository orderMenuItemRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllOrderMenuItemsTest() {
        List<OrderMenuItem> orderMenuItemList = new ArrayList<>();
        orderMenuItemList.add(new OrderMenuItem());
        orderMenuItemList.add(new OrderMenuItem());
        orderMenuItemList.add(new OrderMenuItem());

        when(orderMenuItemRepository.findAll()).thenReturn(orderMenuItemList);

        List<OrderMenuItem> controlList = orderMenuItemService.getOrderMenuItems();

        assertNotNull(controlList);
    }

    @Test
    public void saveNewOrderMenuItemSuccess() {
        OrderMenuItem successOrderMenuItem = new OrderMenuItem();
        when(orderMenuItemRepository.save(successOrderMenuItem)).thenReturn(successOrderMenuItem);

        OrderMenuItem controlOrderMenuItem = orderMenuItemService.saveOrderMenuItem(successOrderMenuItem);

        assertEquals(successOrderMenuItem, controlOrderMenuItem);
    }

    @Test
    public void saveNewOrderMenuItemFail() {
        OrderMenuItem controlOrderMenuItem = orderMenuItemService.saveOrderMenuItem(null);

        assertNull(controlOrderMenuItem);
    }

    @Test
    public void getMenuItemsByOrderIdSuccess() {
        Order order = new Order(1L, OrderStatus.PENDING, new Customer(), new Restaurant());

        MenuItem item1 = new MenuItem("item1",
                12.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRest"));
        MenuItem item2 = new MenuItem("item2",
                13.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRest"));
        MenuItem item3 = new MenuItem("item3",
                14.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRest"));

        OrderMenuItem relationship1 = new OrderMenuItem(order, item1, 1);
        OrderMenuItem relationship2 = new OrderMenuItem(order, item2, 2);
        OrderMenuItem relationship3 = new OrderMenuItem(order, item3, 3);

        List<OrderMenuItem> relationShipsForOrder = new ArrayList<>();
        relationShipsForOrder.add(relationship1);
        relationShipsForOrder.add(relationship2);
        relationShipsForOrder.add(relationship3);

        List<Pair<MenuItem, Integer>> expectedList = new ArrayList<>();
        expectedList.add(Pair.of(item1, 1));
        expectedList.add(Pair.of(item2, 2));
        expectedList.add(Pair.of(item3, 3));

        when(orderMenuItemRepository.getMenuItemsByOrderId(order.getOrderId())).thenReturn(relationShipsForOrder);

        List<Pair<MenuItem, Integer>> controlList = orderMenuItemService.getMenuItemsByOrderId(order.getOrderId());

        assertEquals(expectedList, controlList);
    }

    @Test
    public void getMenuItemsByOrderIdFail() {
        when(orderMenuItemRepository.getMenuItemsByOrderId(null)).thenReturn(null);

        List<Pair<MenuItem, Integer>> controlList = orderMenuItemService.getMenuItemsByOrderId(null);

        assertNull(controlList);
    }

}
