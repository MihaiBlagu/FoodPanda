package FoodPandaBack.repository;

import FoodPandaBack.model.Customer;
import FoodPandaBack.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE r.name =?1")
    Restaurant findByName(String name);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE Restaurant r SET r.name = ?1 WHERE r.restaurantId = ?2")
    void updateAdminRest(String name, Long restId);
}
