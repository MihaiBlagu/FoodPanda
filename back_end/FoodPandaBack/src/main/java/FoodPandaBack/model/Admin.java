package FoodPandaBack.model;

import FoodPandaBack.serializer.AdminSerializer;
import FoodPandaBack.serializer.CustomerSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Observable;
import java.util.Observer;

@Entity
@Table(name = "admin")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "userId")
@JsonSerialize(using = AdminSerializer.class)
public class Admin extends User implements Observer {

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Override
    public void update(Observable o, Object arg) {

    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
