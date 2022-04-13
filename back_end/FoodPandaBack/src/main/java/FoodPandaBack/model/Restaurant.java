package FoodPandaBack.model;

import FoodPandaBack.serializer.DeliveryZoneSerializer;
import FoodPandaBack.serializer.RestaurantSerializer;
import FoodPandaBack.utils.Category;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "restaurantId", scope = Restaurant.class)
@JsonSerialize(using = RestaurantSerializer.class)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Admin admin;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "restaurant_zone",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "zone_id"))
    //@JsonManagedReference
    private Set<DeliveryZone> deliveryZones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", orphanRemoval = true)
    private Set<MenuItem> menu;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", orphanRemoval = true)
    private Set<Order> restaurantOrders;

    public Set<Order> getRestaurantOrders() {
        return restaurantOrders;
    }

    public void setRestaurantOrders(Set<Order> restaurantOrders) {
        this.restaurantOrders = restaurantOrders;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DeliveryZone> getDeliveryZones() {
        return deliveryZones;
    }

    public void setDeliveryZones(Set<DeliveryZone> deliveryZones) {
        this.deliveryZones = deliveryZones;
    }

    public Set<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(Set<MenuItem> menu) {
        this.menu = menu;
    }
}
