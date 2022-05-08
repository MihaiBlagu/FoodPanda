package FoodPandaBack.controller;

import FoodPandaBack.model.Restaurant;
import FoodPandaBack.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        logger.info("Restaurant Controller: Received GET Request - get all restaurants");
        return restaurantService.getRestaurants();
    }

    @PostMapping("/restaurants")
    public Restaurant newRestaurant(@RequestBody Restaurant newRestaurant) {
        logger.info("Restaurant Controller: Received POST Request - insert new restaurant");
        return restaurantService.saveRestaurant(newRestaurant);
    }

    @GetMapping("/restaurants/{name}")
    public Restaurant getRestaurantByName(@PathVariable String name){
        logger.info("Restaurant Controller: Received GET Request - get restaurant with name: " + name);
        return restaurantService.getRestaurantByName(name);
    }

    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurantName(@RequestBody Restaurant newRest, @PathVariable Long restaurantId) {
        logger.info("Restaurant Controller: Received PUT Request - update name of restaurant with id: " + restaurantId);
        restaurantService.updateRestName(newRest.getName(), restaurantId);
    }
}
