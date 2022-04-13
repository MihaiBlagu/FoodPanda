package FoodPandaBack.service;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.Restaurant;
import FoodPandaBack.repository.AdminRepository;
import FoodPandaBack.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    public RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant saveRestaurant(Restaurant newRestaurant) {
        return restaurantRepository.save(newRestaurant);
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public void updateAdminRest(String name, long restId) {
        restaurantRepository.updateAdminRest(name, restId);
    }
}
