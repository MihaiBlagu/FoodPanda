package FoodPandaBack.controller;

import FoodPandaBack.model.Order;
import FoodPandaBack.service.OrderService;
import FoodPandaBack.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/orders")
    public List<Order> getOrders() {
        logger.info("Order Controller: Received GET Request - get all orders");
        return orderService.getOrders();
    }

    @PostMapping("/orders")
    public Order newOrder(@RequestBody Order newOrder) {
        logger.info("Order Controller: Received POST Request - insert new order");
        return orderService.saveOrder(newOrder);
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getOrderByCustomerId(@PathVariable Long userId) {
        logger.info("Order Controller: Received GET Request - get orders by user id");
        return orderService.getOrdersByCustomerId(userId);
    }

    @GetMapping("/orders/restaurant/{restaurantId}")
    public List<Order> getOrdersByRestId(@PathVariable Long restaurantId) {
        logger.info("Order Controller: Received GET Request - get orders by restaurant id");
        return orderService.getOrdersByRestId(restaurantId);
    }

    @GetMapping("/orders/restaurant/{restaurantId}/{orderStatus}")
    public List<Order> getFilteredOrdersByStatus(@PathVariable Long restaurantId,
                                                 @PathVariable("orderStatus") OrderStatus orderStatus) {

        logger.info("Order Controller: Received GET Request - filter orders by status: " + orderStatus);
        return orderService.getFilteredOrdersByStatus(restaurantId, orderStatus);
    }

}
