package FoodPandaBack.service;

import FoodPandaBack.model.Order;
import FoodPandaBack.repository.OrderRepository;
import FoodPandaBack.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    /**
     * Gets list of all orders
     *
     * @return list of all orders
     */
    public List<Order> getOrders() {
        try{
            List<Order> l = orderRepository.findAll();
            logger.info("Order Service: Successful GET request - get all orders; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("Order Service: Failed GET Request - get all orders - " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves a new Order to the database
     *
     * @param newOrder the Order to be saved
     * @return the saved Order
     */
    public Order saveOrder(Order newOrder) {
        try{
            Order o = orderRepository.save(newOrder);
            logger.info("Order Service: Successful POST request - insert new order with id: " + o.getOrderId());
            return o;
        } catch (Exception e) {
            logger.error("Order Service: Failed POST Request - insert new order - " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets all Orders of the Customer with the given id
     *
     * @param userId the userId of the Customer
     * @return the list of Orders corresponding to the given userId
     */
    public List<Order> getOrdersByCustomerId(Long userId) {
        try{
            List<Order> l = orderRepository.getOrderByCustomerId(userId);
            logger.info("Order Service: Successful GET request - get orders by user id; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("Order Service: Failed GET Request - get orders by user id - " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets all Orders of the Restaurant with the given id
     *
     * @param restaurantId the id of the Restaurant
     * @return the list of Orders corresponding to the restaurant with the given id
     */
    public List<Order> getOrdersByRestId(Long restaurantId) {
        try{
            List<Order> l = orderRepository.getOrdersByRestId(restaurantId);
            logger.info("Order Service: Successful GET request - get orders by restaurant id; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("Order Service: Failed GET Request - get orders by restaurant id - " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets all Orders of the Restaurant with the given id that have a certain status
     *
     * @param restaurantId the id of the Restaurant
     * @param orderStatus the status the Orders get filtered by
     * @return the list of Orders with the given status corresponding to the Restaurant with the given id
     */
    public List<Order> getFilteredOrdersByStatus(Long restaurantId, OrderStatus orderStatus) {
        try{
            List<Order> l = orderRepository.getOrdersByRestId(restaurantId)
                    .stream()
                    .filter(o -> o.getOrderStatus() == orderStatus)
                    .collect(Collectors.toList());
            logger.info("Order Service: Successful GET request - filter orders by status; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("Order Service: Failed GET Request - filter orders by status - " + e.getMessage());
            return null;
        }
    }
}
