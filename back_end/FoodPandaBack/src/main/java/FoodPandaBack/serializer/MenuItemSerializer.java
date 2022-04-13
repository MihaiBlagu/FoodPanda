package FoodPandaBack.serializer;

import FoodPandaBack.model.MenuItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class MenuItemSerializer extends StdSerializer<MenuItem> {
    public MenuItemSerializer() {
        this(null);
    }

    public MenuItemSerializer(Class<MenuItem> m) {
        super(m);
    }

    @Override
    public void serialize(MenuItem m, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("itemId", m.getItemId());
        jgen.writeStringField("title", m.getTitle());
        jgen.writeNumberField("price", m.getPrice());
        jgen.writeNumberField("category", m.getCategory().ordinal());
        //jgen.writeNumberField("restaurantId", m.getRestaurant().getRestaurantId());
        jgen.writeFieldName("restaurant");
        jgen.writeObject(m.getRestaurant());
        jgen.writeEndObject();
    }
}
