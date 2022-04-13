package FoodPandaBack.controller;

import FoodPandaBack.model.MenuItem;
import FoodPandaBack.model.Order;
import FoodPandaBack.model.OrderMenuItem;
import FoodPandaBack.service.MenuItemService;
import FoodPandaBack.service.OrderMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class OrderMenuItemController {

    @Autowired
    private OrderMenuItemService orderMenuItemService;

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("/order-menu-item")
    public List<OrderMenuItem> getOrderMenuItems(){
        return orderMenuItemService.getOrderMenuItems();
    }

    @PostMapping("/order-menu-item")
    public OrderMenuItem saveOrderMenuItem(@RequestBody OrderMenuItem newO){
        return orderMenuItemService.saveOrderMenuItem(newO);
    }

    @GetMapping("/order-menu-item/{orderId}")
    public List<Pair<MenuItem, Integer>> getOrderMenuItemsByOrderId(@PathVariable Long orderId){
        return orderMenuItemService.getMenuItemsByOrderId(orderId)
                .stream()
                .map(oi -> Pair.of(oi.getMenuItem(), oi.getNbOfItems()))
                .collect(Collectors.toList());

//        List<OrderMenuItem> oi = orderMenuItemService.getMenuItemsByOrderId(orderId);
//        ArrayList<Pair<MenuItem, Integer>> result = new ArrayList<>();
//        for (OrderMenuItem o : oi) {
//            result.add(Pair.of(o.getMenuItem(), o.getNbOfItems()));
//        }
//
//        return result;
    }
}
