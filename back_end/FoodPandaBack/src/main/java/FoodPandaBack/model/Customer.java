package FoodPandaBack.model;

import FoodPandaBack.serializer.CustomerSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "userId")
@JsonSerialize(using = CustomerSerializer.class)
public class Customer extends User {

    // do it so the most recent one is at the front
    // one to many
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    private Set<Order> customerOrders;

    // foods in cart
    // many to many
    @ManyToMany
    @JoinTable(
            name = "customer_item",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<MenuItem> itemsInCart;

    private void addItemToCart(MenuItem item){
        //need to check whether they are from the same restaurant or not
    }

    private void removeItemFromCart(MenuItem item){

    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

}

