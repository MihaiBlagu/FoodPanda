package FoodPandaBack.service;

import FoodPandaBack.model.MenuItem;
import FoodPandaBack.model.OrderMenuItem;
import FoodPandaBack.repository.OrderMenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMenuItemService {

    @Autowired
    private OrderMenuItemRepository orderMenuItemRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderMenuItemService.class);

    /**
     * Gets list of all OrderMenuItems
     *
     * @return lst of OrderMenuItems
     */
    public List<OrderMenuItem> getOrderMenuItems() {
        try{
            List<OrderMenuItem> l = orderMenuItemRepository.findAll();
            logger.info("OrderMenuItem Service: Successful GET request - get all relationships; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("OrderMenuItem Service: Failed GET Request - get all relationships - " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves new OrderMenuItem to the database
     *
     * @param newO the new OrderMenuItem
     * @return the saved OrderMenuItem
     */
    public OrderMenuItem saveOrderMenuItem(OrderMenuItem newO) {
        try{
            OrderMenuItem o = orderMenuItemRepository.save(newO);
            logger.info("OrderMenuItem Service: Successful POST request - inserted new relationship with id:"
                    + o.getRelationshipId());
            return o;
        } catch (Exception e) {
            logger.error("OrderMenuItem Service: Failed POST Request - insert new relationship - " + e.getMessage());
            return null;
        }
    }


    /**
     * Gets list of MenuItems and their quantities given an orderId
     *
     * @param orderId the orderId we want to retrieve items from
     * @return the list of items and their quantities
     */
    public List<Pair<MenuItem, Integer>> getMenuItemsByOrderId(Long orderId) {
        try{
            List<Pair<MenuItem, Integer>> l = orderMenuItemRepository.getMenuItemsByOrderId(orderId)
                    .stream()
                    .map(oi -> Pair.of(oi.getMenuItem(), oi.getNbOfItems()))
                    .collect(Collectors.toList());
            logger.info("OrderMenuItem Service: Successful GET request - get items for order with id:" + orderId +
                    "; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("OrderMenuItem Service: Failed GET Request - get items for order with id: " + orderId+ " - "
                    + e.getMessage());
            return null;
        }
    }
}
