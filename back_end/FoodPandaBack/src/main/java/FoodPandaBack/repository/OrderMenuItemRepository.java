package FoodPandaBack.repository;

import FoodPandaBack.model.OrderMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, Long> {

    @Query("SELECT oi FROM OrderMenuItem oi " +
            "JOIN MenuItem m ON m.itemId = oi.menuItem.itemId " +
            "WHERE oi.order.orderId =?1")
    List<OrderMenuItem> getMenuItemsByOrderId(Long orderId);
}
