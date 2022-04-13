package FoodPandaBack.controller;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.Customer;
import FoodPandaBack.model.Restaurant;
import FoodPandaBack.service.CustomerService;
import FoodPandaBack.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
//        for(Restaurant r : restaurantService.getRestaurants()){
//            System.out.println(r.ge);
//        }
        return restaurantService.getRestaurants();
    }

    @PostMapping("/restaurants")
    public Restaurant newRestaurant(@RequestBody Restaurant newRestaurant) {
        return restaurantService.saveRestaurant(newRestaurant);
    }

    @GetMapping("/restaurants/{name}")
    public Restaurant getRestaurantByName(@PathVariable String name){
        return restaurantService.getRestaurantByName(name);
    }

    @PutMapping("/restaurants/{restaurantId}")
    public void updateAdmin(@RequestBody Restaurant newRest, @PathVariable Long restaurantId) {
        restaurantService.updateAdminRest(newRest.getName(), restaurantId);
    }
}
