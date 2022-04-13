package FoodPandaBack.service;

import FoodPandaBack.model.OrderMenuItem;
import FoodPandaBack.repository.OrderMenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMenuItemService {

    @Autowired
    private OrderMenuItemRepository orderMenuItemRepository;

    public List<OrderMenuItem> getOrderMenuItems() {
        return orderMenuItemRepository.findAll();
    }

    public OrderMenuItem saveOrderMenuItem(OrderMenuItem newO) {
        return orderMenuItemRepository.save(newO);
    }

    public List<OrderMenuItem> getMenuItemsByOrderId(Long orderId) {
        return orderMenuItemRepository.getMenuItemsByOrderId(orderId);
    }
}
