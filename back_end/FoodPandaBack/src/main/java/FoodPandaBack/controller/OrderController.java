package FoodPandaBack.controller;

import FoodPandaBack.model.Order;
import FoodPandaBack.model.OrderMenuItem;
import FoodPandaBack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("/orders")
    public Order newOrder(@RequestBody Order newOrder){
        return orderService.saveOrder(newOrder);
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getOrderByCustomerId(@PathVariable Long userId){
        return orderService.getOrderByCustomerId(userId);
    }

    @GetMapping("/orders/restaurant/{restaurantId}")
    public List<Order> getOrdersByRestId(@PathVariable Long restaurantId){
        return orderService.getOrdersByRestId(restaurantId);
    }

}
