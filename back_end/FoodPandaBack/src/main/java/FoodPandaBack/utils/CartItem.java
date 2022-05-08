package FoodPandaBack.utils;

import FoodPandaBack.model.MenuItem;

public class CartItem {
    
    private MenuItem item;
    private Integer nbOfItems;

    public CartItem() {
    }

    public CartItem(MenuItem item, Integer nbOfItems) {
        this.item = item;
        this.nbOfItems = nbOfItems;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public Integer getNbOfItems() {
        return nbOfItems;
    }

    public void setNbOfItems(Integer nbOfItems) {
        this.nbOfItems = nbOfItems;
    }
}
