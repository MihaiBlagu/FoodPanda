package FoodPandaBack.repository;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.username =?1")
    Admin findByUsername(String username);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE Admin a SET a.restaurant = ?1 WHERE a.userId = ?2")
    void updateAdminRest(Restaurant r, Long adminId);
}
