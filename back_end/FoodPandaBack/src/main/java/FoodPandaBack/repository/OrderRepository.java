package FoodPandaBack.repository;

import FoodPandaBack.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.customer.userId =?1")
    List<Order> getOrderByCustomerId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.restaurant.restaurantId =?1")
    List<Order> getOrdersByRestId(Long restaurantId);
}
