package FoodPandaBack.controller;

import FoodPandaBack.model.MenuItem;
import FoodPandaBack.model.OrderMenuItem;
import FoodPandaBack.service.OrderMenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderMenuItemController {

    @Autowired
    private OrderMenuItemService orderMenuItemService;

    private final Logger logger = LoggerFactory.getLogger(OrderMenuItemController.class);

    @GetMapping("/order-menu-item")
    public List<OrderMenuItem> getOrderMenuItems(){
        logger.info("OrderMenuItem Controller: Received GET Request - get all relationships");
        return orderMenuItemService.getOrderMenuItems();
    }

    @PostMapping("/order-menu-item")
    public OrderMenuItem saveOrderMenuItem(@RequestBody OrderMenuItem newO) {
        logger.info("OrderMenuItem Controller: Received POST Request - insert new relationship");
        return orderMenuItemService.saveOrderMenuItem(newO);
    }

    @GetMapping("/order-menu-item/{orderId}")
    public List<Pair<MenuItem, Integer>> getMenuItemsByOrderId(@PathVariable Long orderId) {
        logger.info("OrderMenuItem Controller: Received GET Request - get items for order with id: " + orderId);
        return orderMenuItemService.getMenuItemsByOrderId(orderId);
    }
}
