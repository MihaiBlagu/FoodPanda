package FoodPandaBack.controller;

import FoodPandaBack.model.MenuItem;
import FoodPandaBack.service.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    private final Logger logger = LoggerFactory.getLogger(MenuItemController.class);

    @GetMapping("/menu-items")
    public List<MenuItem> getMenuItems() {
        logger.info("MenuItem Controller: Received GET Request - get all menu items");
        return menuItemService.getMenuItems();
    }

    @PostMapping("/menu-items")
    public MenuItem newMenuItem(@RequestBody MenuItem newMenuItem) {
        logger.info("MenuItem Controller: Received POST Request - insert new menu item");
        return menuItemService.saveMenuItem(newMenuItem);
    }

    @GetMapping("/menu-items/{restaurantId}/{title}")
    public MenuItem getMenuItemByRestIdAndTitle(@PathVariable Long restaurantId, @PathVariable String title){
        logger.info("MenuItem Controller: Received GET Request - get menu item by restaurant id and item title");
        return menuItemService.getMenuItemByRestIdAndTitle(restaurantId, title);
    }

    @GetMapping("/menu-items/{restaurantId}")
    public List<MenuItem> getMenuItemsByRestId(@PathVariable Long restaurantId){
        logger.info("MenuItem Controller: Received GET Request - get items by restaurant id");
        return menuItemService.getMenuItemsByRestId(restaurantId);
    }
}
