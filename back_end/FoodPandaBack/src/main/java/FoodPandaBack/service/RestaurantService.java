package FoodPandaBack.service;

import FoodPandaBack.model.Restaurant;
import FoodPandaBack.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    public RestaurantRepository restaurantRepository;

    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    /**
     * Gets list of all Restaurants
     *
     * @return the list of all Restaurants
     */
    public List<Restaurant> getRestaurants() {
        try{
            List<Restaurant> l = restaurantRepository.findAll();
            logger.info("Restaurant Service: Successful GET request - get all restaurants; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("Restaurant Service: Failed GET Request - get all restaurants - " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves a new Restaurant to the database
     *
     * @param newRestaurant the Restaurant to be saved
     * @return the saved Restaurant
     */
    public Restaurant saveRestaurant(Restaurant newRestaurant) {
        try{
            Restaurant r = restaurantRepository.save(newRestaurant);
            logger.info("Restaurant Service: Successful POST request - insert new restaurant with name: "
                    + r.getName());
            return r;
        } catch (Exception e) {
            logger.error("Restaurant Service: Failed POST Request - insert new restaurant - " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets the Restaurant with a given name
     *
     * @param name the name of the Restaurant
     * @return the Restaurant with the given name
     */
    public Restaurant getRestaurantByName(String name) {
        try{
            Restaurant r = restaurantRepository.findByName(name);
            logger.info("Restaurant Service: Successful GET request - get restaurant with name: " + name);
            return r;
        } catch (Exception e) {
            logger.error("Restaurant Service: Failed GET Request - get restaurant by name - " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates the name of the Restaurant with the given id
     *
     * @param name the new name of the Restaurant
     * @param restId the id of the Restaurant
     * @throws NullPointerException if the id of the Restaurant is null
     */
    public void updateRestName(String name, Long restId) throws NullPointerException {
        if(restId == null) {
            throw new NullPointerException();
        }
        try{
            restaurantRepository.updateRestName(name, restId);
            logger.info("Restaurant Service: Successful PUT request - update name of restaurant with id: "
                    + restId);
        } catch (Exception e) {
            logger.error("Restaurant Service: Failed PUT Request - update restaurant name - " + e.getMessage());
        }
    }
}
