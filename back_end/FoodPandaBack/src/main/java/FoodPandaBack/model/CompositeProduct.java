package FoodPandaBack.model;

import FoodPandaBack.utils.Category;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

//@Entity
//@Table(name = "composite_product")
public class CompositeProduct extends MenuItem {

    // one composite can have many menuitems -> onetomany
    // change to set
    private ArrayList<MenuItem> items;

    public CompositeProduct(String title, ArrayList<MenuItem> items, Category category){
        double price = 0.0;
        for(MenuItem i : items){
            price += i.getPrice();
        }
        this.price = price;
        this.category = category;
        this.items = items;
        this.title = title;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

//    public String printIngredients(){
//        StringBuilder text = new StringBuilder();
//
//        int size = this.items.size();
//        int currentItem = 0;
//
//        for(MenuItem i : this.items){
//            if(i instanceof BaseProduct){
//                text.append(i.getTitle());
//
//                if(currentItem != size - 1){
//                    text.append(", ");
//                }
//            } else if(i instanceof CompositeProduct) {
//                text.append(i.getTitle());
//                text.append("(Consists of: ");
//                text.append(((CompositeProduct) i).printIngredients());
//                text.append(")");
//
//                if(currentItem != size - 1){
//                    text.append(", ");
//                }
//            }
//
//            currentItem++;
//        }
//
//        return text.toString();
//    }

    @Override
    public double getPrice(){
        double totalPrice = 0;
        for(MenuItem i : items){
            totalPrice += i.getPrice();
        }

        return totalPrice;
    }

    public void modifyComposite(MenuItem item){
        double rating = 0.0;
        int calories = 0;
        double protein = 0.0;
        double fat = 0.0;
        double sodium = 0.0;
        double price = 0.0;
//        for(MenuItem i : items){
//            if(i.getTitle().equals(item.getTitle())){
//                i.setRating(item.getRating());
//                i.setCalories(item.getCalories());
//                i.setProteins(item.getProteins());
//                i.setFats(item.getFats());
//                i.setSodium(item.getSodium());
//                i.setPrice(item.getPrice());
//            }
//
//            rating += i.getRating();
//            calories += i.getCalories();
//            protein += i.getProteins();
//            fat += i.getFats();
//            sodium += i.getSodium();
//            price += i.getPrice();
//        }
//
//        this.rating = rating / items.size();
//        this.calories = calories;
//        this.proteins = protein;
//        this.fats = fat;
//        this.sodium = sodium;
//        this.price = price;
    }

}
