package FoodPandaBack.service;

import FoodPandaBack.model.Restaurant;
import FoodPandaBack.repository.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllRestaurantsSucess() {
        List<Restaurant> restList = new ArrayList<>();
        restList.add(new Restaurant());
        restList.add(new Restaurant());
        restList.add(new Restaurant());

        when(restaurantRepository.findAll()).thenReturn(restList);

        List<Restaurant> controlList = restaurantService.getRestaurants();

        assertNotNull(controlList);
    }

    @Test
    public void saveNewRestaurantSuccess() {
        Restaurant successRestaurant = new Restaurant(1L, "successRestaurant");

        when(restaurantRepository.save(successRestaurant)).thenReturn(successRestaurant);

        Restaurant controlRestaurant = restaurantService.saveRestaurant(successRestaurant);

        assertEquals(successRestaurant, controlRestaurant);
    }

    @Test(expected = Exception.class)
    public void saveNewOrderFail() {
        when(restaurantService.saveRestaurant(null)).thenThrow(new Exception());

        Restaurant controlRestaurant = restaurantService.saveRestaurant(null);

        assertNull(controlRestaurant);
    }

    @Test
    public void getRestaurantByNameSuccess() {
        Restaurant successRestaurant = new Restaurant();
        successRestaurant.setName("restaurant1");

        when(restaurantRepository.findByName("restaurant1")).thenReturn(successRestaurant);

        Restaurant controlRestaurant = restaurantService.getRestaurantByName("restaurant1");

        assertEquals("restaurant1", controlRestaurant.getName());
    }

    @Test
    public void getRestaurantByNameFail() {
        when(restaurantRepository.findByName(null)).thenReturn(null);

        Restaurant controlRestaurant = restaurantService.getRestaurantByName(null);

        assertNull(controlRestaurant);
    }

    @Test
    public void updateRestaurantNameSuccess() {
        Restaurant updatedRestaurant = new Restaurant(1L, "oldName");

        restaurantService.updateRestName("newName", updatedRestaurant.getRestaurantId());

        updatedRestaurant.setName("newName");
        when(restaurantRepository.findByName("newName")).thenReturn(updatedRestaurant);

        Restaurant controlRestaurant = restaurantService.getRestaurantByName("newName");
        assertNotNull(controlRestaurant);
    }

    @Test(expected = NullPointerException.class)
    public void updateRestaurantNameFail() {
        restaurantService.updateRestName("newName", null);
    }
}
