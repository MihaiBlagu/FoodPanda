package FoodPandaBack.serializer;

import FoodPandaBack.model.Order;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OrderSerializer extends StdSerializer<Order> {

    public OrderSerializer() {
        this(null);
    }

    public OrderSerializer(Class<Order> o) {
        super(o);
    }

    @Override
    public void serialize(Order o, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("orderId", o.getOrderId());
        jgen.writeNumberField("orderStatus", o.getOrderStatus().ordinal());
        jgen.writeFieldName("customer");
        jgen.writeObject(o.getCustomer());
        jgen.writeFieldName("restaurantId");
        jgen.writeObject(o.getRestaurant().getRestaurantId());

//        jgen.writeFieldName("menuItems");
//        jgen.writeStartArray();
//        for(OrderMenuItem m : o.getMenuItemAssoc()) {
//            jgen.writeObject(m.getMenuItem());
//        }
//        jgen.writeEndArray();

        jgen.writeEndObject();
    }
}
