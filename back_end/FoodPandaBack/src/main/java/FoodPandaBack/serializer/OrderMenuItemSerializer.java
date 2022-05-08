package FoodPandaBack.serializer;

import FoodPandaBack.model.OrderMenuItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OrderMenuItemSerializer extends StdSerializer<OrderMenuItem> {

    public OrderMenuItemSerializer() {
        this(null);
    }

    public OrderMenuItemSerializer(Class<OrderMenuItem> o) {
        super(o);
    }

    @Override
    public void serialize(OrderMenuItem o, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("relationshipId", o.getRelationshipId());
        jgen.writeNumberField("nbOfItems", o.getNbOfItems());
        jgen.writeNumberField("orderId", o.getOrder().getOrderId());
        jgen.writeNumberField("menuItemId", o.getMenuItem().getItemId());

        jgen.writeEndObject();
    }
}
