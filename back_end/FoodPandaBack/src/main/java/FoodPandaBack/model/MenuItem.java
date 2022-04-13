package FoodPandaBack.model;


import FoodPandaBack.serializer.MenuItemSerializer;
import FoodPandaBack.utils.Category;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "menu_item",
uniqueConstraints = @UniqueConstraint(
        name = "UniqueTitleAndRestaurant",
        columnNames = { "title", "restaurant_id" }
))
//@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "itemId")
@JsonSerialize(using = MenuItemSerializer.class)
public class MenuItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    protected String title;

    @Column(nullable = false)
    protected double price;

    @Column(nullable = false)
    protected Category category;

    @ManyToMany(mappedBy = "itemsInCart")
    //@JsonIdentityReference
    // probably needs jsonback reference
    protected Set<Customer> customers;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    //@JsonIdentityReference
    protected Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem")
    private Set<OrderMenuItem> orderAssoc;

    public MenuItem() {
    }


    public MenuItem(String title, double price, Category category, Restaurant restaurant) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<OrderMenuItem> getOrderAssoc() {
        return orderAssoc;
    }

    public void setOrderAssoc(Set<OrderMenuItem> orderAssoc) {
        this.orderAssoc = orderAssoc;
    }
}
