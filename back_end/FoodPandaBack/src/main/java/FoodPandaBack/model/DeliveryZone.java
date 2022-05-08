package FoodPandaBack.model;

import FoodPandaBack.serializer.DeliveryZoneSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "delivery_zone")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property  = "@id", scope = DeliveryZone.class)
@JsonSerialize(using = DeliveryZoneSerializer.class)
public class DeliveryZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneId;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "deliveryZones")
    @JsonIgnore
    protected Set<Restaurant> restaurants;

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
