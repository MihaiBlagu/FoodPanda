package FoodPandaBack.model;

import FoodPandaBack.serializer.OrderMenuItemSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "order_menu_item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "relationshipId", scope = OrderMenuItem.class)
@JsonSerialize(using = OrderMenuItemSerializer.class)
public class OrderMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relationshipId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", referencedColumnName = "itemId", nullable = false)
    private MenuItem menuItem;

    @Column(name = "nb_of_items", nullable = false)
    private Integer nbOfItems;

    public OrderMenuItem() {
    }

    public OrderMenuItem(Order order, MenuItem menuItem, Integer nbOfItems) {
        this.order = order;
        this.menuItem = menuItem;
        this.nbOfItems = nbOfItems;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getNbOfItems() {
        return nbOfItems;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public void setNbOfItems(Integer nbOfItems) {
        this.nbOfItems = nbOfItems;
    }


}
