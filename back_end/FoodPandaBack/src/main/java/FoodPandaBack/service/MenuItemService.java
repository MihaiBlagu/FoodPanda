package FoodPandaBack.service;

import FoodPandaBack.model.MenuItem;
import FoodPandaBack.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    @Autowired
    public MenuItemRepository menuItemRepository;

    public List<MenuItem> getMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem saveMenuItem(MenuItem newMenuItem) {
        return menuItemRepository.save(newMenuItem);
    }

    public MenuItem getMenuItemByRestIdAndTitle(Long restaurantId, String title) {
        return menuItemRepository.findByRestIdAndTitle(restaurantId, title);
    }

    public List<MenuItem> getMenuItemsByRestId(Long restaurantId) {
        return menuItemRepository.findAllByRestId(restaurantId);
    }

    public Optional<MenuItem> getMenuItemById(Long itemId) {
        return menuItemRepository.findById(itemId);
    }
}
