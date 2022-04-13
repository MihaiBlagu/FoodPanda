package FoodPandaBack.controller;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.MenuItem;
import FoodPandaBack.service.AdminService;
import FoodPandaBack.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("/menu-items")
    public List<MenuItem> getMenuItems(){
        return menuItemService.getMenuItems();
    }

    @PostMapping("/menu-items")
    public MenuItem newMenuItem(@RequestBody MenuItem newMenuItem) {
        return menuItemService.saveMenuItem(newMenuItem);
    }

    @GetMapping("/menu-items/{restaurantId}/{title}")
    public MenuItem getMenuItemByUsername(@PathVariable Long restaurantId, @PathVariable String title){
        return menuItemService.getMenuItemByRestIdAndTitle(restaurantId, title);
    }

    @GetMapping("/menu-items/{restaurantId}")
    public List<MenuItem> getMenuItemsByRestId(@PathVariable Long restaurantId){
        return menuItemService.getMenuItemsByRestId(restaurantId);
    }
}
