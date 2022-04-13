package FoodPandaBack.service;

import FoodPandaBack.model.Order;
import FoodPandaBack.repository.OrderMenuItemRepository;
import FoodPandaBack.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMenuItemRepository orderMenuItemRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    public List<Order> getOrderByCustomerId(Long userId) {
        return orderRepository.getOrderByCustomerId(userId);
    }

    public List<Order> getOrdersByRestId(Long restaurantId) {
        return orderRepository.getOrdersByRestId(restaurantId);
    }
}
