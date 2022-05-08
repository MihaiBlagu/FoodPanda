package FoodPandaBack.service;

import FoodPandaBack.model.MenuItem;
import FoodPandaBack.model.Restaurant;
import FoodPandaBack.repository.MenuItemRepository;
import FoodPandaBack.utils.Category;
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
public class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    MenuItemRepository menuItemRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllMenuItemsTest() {
        List<MenuItem> itemList = new ArrayList<>();
        itemList.add(new MenuItem());
        itemList.add(new MenuItem());
        itemList.add(new MenuItem());

        when(menuItemRepository.findAll()).thenReturn(itemList);

        List<MenuItem> controlList = menuItemService.getMenuItems();

        assertNotNull(controlList);
    }

    @Test
    public void saveNewMenuItemSuccess() {
        MenuItem successItem = new MenuItem("successItem",
                12.0,
                Category.BREAKFAST,
                new Restaurant());

        when(menuItemRepository.save(successItem)).thenReturn(successItem);

        MenuItem controlItem = menuItemService.saveMenuItem(successItem);

        assertEquals(successItem, controlItem);
    }

    @Test(expected = Exception.class)
    public void saveNewMenuItemFail() {
        when(menuItemService.saveMenuItem(null)).thenThrow(new Exception());

        MenuItem controlItem = menuItemService.saveMenuItem(null);

        assertNull(controlItem);
    }

    @Test
    public void getItemByRestIdAndTitleSuccess() {
        MenuItem itemInDatabase = new MenuItem("newItem",
                12.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRest"));

        when(menuItemRepository.findByRestIdAndTitle(
                itemInDatabase.getRestaurant().getRestaurantId(),
                itemInDatabase.getTitle()))
                .thenReturn(itemInDatabase);

        MenuItem controlIItem = menuItemService.getMenuItemByRestIdAndTitle(
                itemInDatabase.getRestaurant().getRestaurantId(),
                itemInDatabase.getTitle());

        assertEquals(itemInDatabase, controlIItem);
    }

    @Test
    public void getItemByRestIdAndTitleFail() {
        when(menuItemRepository.findByRestIdAndTitle(1L, "randomTitle")).thenReturn(null);

        MenuItem controlItem = menuItemService.getMenuItemByRestIdAndTitle(1L, "randomTitle");

        assertNull(controlItem);
    }

    @Test
    public void getItemsByRestIdSuccess() {
        MenuItem item1 = new MenuItem("item1",
                12.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRest"));
        MenuItem item2 = new MenuItem("item2",
                12.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRest"));
        List<MenuItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        when(menuItemRepository.findAllByRestId(item1.getRestaurant().getRestaurantId())).thenReturn(items);

        List<MenuItem> controlIList = menuItemService.getMenuItemsByRestId(item1.getRestaurant().getRestaurantId());

        assertEquals(controlIList, items);
    }

    @Test
    public void getItemsByRestIdFail() {
        when(menuItemRepository.findAllByRestId(1L)).thenReturn(null);

        List<MenuItem> controlIList = menuItemService.getMenuItemsByRestId(1L);

        assertNull(controlIList);
    }

    @Test
    public void saveNewAdminSuccess() {
        MenuItem successItem = new MenuItem("sucessItem",
                12.0,
                Category.BREAKFAST,
                new Restaurant(1L, "newRest"));

        when(menuItemRepository.save(successItem)).thenReturn(successItem);

        MenuItem controlItem = menuItemService.saveMenuItem(successItem);

        assertEquals(controlItem, successItem);
    }

    @Test(expected = Exception.class)
    public void saveNewAdminFail() {
        when(menuItemService.saveMenuItem(null)).thenThrow(new Exception());

        MenuItem controlItem = menuItemService.saveMenuItem(null);

        assertNull(controlItem);
    }
}
