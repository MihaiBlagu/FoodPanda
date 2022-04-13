package FoodPandaBack.model;

import FoodPandaBack.utils.Category;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "base_product")
public class BaseProduct extends MenuItem {

    public BaseProduct(){
    }

    public BaseProduct(String title, double price, Category category, Restaurant restaurant) {
        super(title, price, category, restaurant);
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public double getPrice() {
        return super.getPrice();
    }

    public void setPrice(double price) {
        super.setPrice(price);
    }
}
