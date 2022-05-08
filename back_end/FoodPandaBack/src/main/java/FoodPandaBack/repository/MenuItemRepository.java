package FoodPandaBack.repository;

import FoodPandaBack.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.name =?1 AND m.title =?2")
    MenuItem findByRestIdAndTitle(Long restaurantId, String title);

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.restaurantId =?1 " +
            "ORDER BY m.category")
    List<MenuItem> findAllByRestId(Long restaurantId);
}
