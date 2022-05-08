package FoodPandaBack.service;

import FoodPandaBack.model.MenuItem;
import FoodPandaBack.repository.MenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    @Autowired
    public MenuItemRepository menuItemRepository;

    private final Logger logger = LoggerFactory.getLogger(MenuItemService.class);

    /**
     * Gets list of all MenuItems
     *
     * @return list of MenuItems
     */
    public List<MenuItem> getMenuItems() {
        try{
            List<MenuItem> l = menuItemRepository.findAll();
            logger.info("MenuItem Service: Successful GET request - get all menu items; list size: " + l.size());
            return l;
        } catch (Exception e) {
            logger.error("MenuItem Service: Failed GET Request - get all menu items - " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves a new MenuItem to the database
     *
     * @param newMenuItem the MenuItem that the controller resolved from the passed json
     * @return the MenuItem that was saved
     */
    public MenuItem saveMenuItem(MenuItem newMenuItem) {
        try{
            MenuItem m = menuItemRepository.save(newMenuItem);
            logger.info("MenuItem Service: Successful POST request - insert new menu item with title: " + m.getTitle());
            return m;
        } catch (Exception e) {
            logger.error("MenuItem Service: Failed POST Request - insert new menu item - " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets MenuItem by restaurantId and MenuItem title
     *
     * @param restaurantId id of restaurant the MenuItem gets searched by
     * @param title title of MenuItem
     * @return MenuItem that was found
     */
    public MenuItem getMenuItemByRestIdAndTitle(Long restaurantId, String title) {
        try{
            MenuItem m = menuItemRepository.findByRestIdAndTitle(restaurantId, title);
            logger.info("MenuItem Service: Successful GET request - get menu item with restaurant id: '" +
                    m.getRestaurant().getRestaurantId() + "' and item title: '" + m.getTitle() + "'");
            return m;
        } catch (Exception e) {
            logger.error("MenuItem Service: Failed GET Request - get menu item by restaurant id and item title - "
                    + e.getMessage());
            return null;
        }
    }

    /**
     * Gets list of MenuItem by restaurantId
     *
     * @param restaurantId id of restaurant the MenuItems are from
     * @return list of MenuItems from that restaurant
     */
    public List<MenuItem> getMenuItemsByRestId(Long restaurantId) {
        try{
            List<MenuItem> l = menuItemRepository.findAllByRestId(restaurantId);
            logger.info("MenuItem Service: Successful GET request - get items by restaurant id; list size: "
                    + l.size());
            return l;
        } catch (Exception e) {
            logger.error("MenuItem Service: Failed GET Request - get items by restaurant id - " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets MenuItem by id
     *
     * @param itemId id of MenuItem that needs to be found
     * @return MenuItem with given id
     */
    public Optional<MenuItem> getMenuItemById(Long itemId) {
        return menuItemRepository.findById(itemId);
    }
}
