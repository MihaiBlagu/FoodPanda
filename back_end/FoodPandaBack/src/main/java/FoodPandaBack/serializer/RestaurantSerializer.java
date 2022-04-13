package FoodPandaBack.serializer;

import FoodPandaBack.model.DeliveryZone;
import FoodPandaBack.model.MenuItem;
import FoodPandaBack.model.Restaurant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RestaurantSerializer extends StdSerializer<Restaurant> {
    public RestaurantSerializer() {
        this(null);
    }

    public RestaurantSerializer(Class<Restaurant> r) {
        super(r);
    }

    @Override
    public void serialize(Restaurant r, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("restaurantId", r.getRestaurantId());
        //jgen.writeFieldName("admin");
        //jgen.writeObject(r.getAdmin());
        jgen.writeStringField("name", r.getName());

        jgen.writeFieldName("deliveryZones");
        jgen.writeStartArray();
        for(DeliveryZone d : r.getDeliveryZones()) {
            jgen.writeObject(d);
        }
        jgen.writeEndArray();

        /*jgen.writeFieldName("menu");
        jgen.writeStartArray();
        for(MenuItem m : r.getMenu().stream().toList()){
            //jgen.writeObject(m);
            jgen.writeNumber(m.getItemId());
        }
        jgen.writeEndArray();*/

        jgen.writeEndObject();
    }
}
