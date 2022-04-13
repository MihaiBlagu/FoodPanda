package FoodPandaBack.serializer;

import FoodPandaBack.model.Admin;
import FoodPandaBack.model.Customer;
import FoodPandaBack.model.MenuItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AdminSerializer extends StdSerializer<Admin> {
    public AdminSerializer() {
        this(null);
    }

    public AdminSerializer(Class<Admin> a) {
        super(a);
    }

    @Override
    public void serialize(Admin a, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("userId", a.getUserId());
        jgen.writeStringField("username", a.getUsername());
        jgen.writeStringField("password", a.getPassword());
        jgen.writeFieldName("restaurant");
        if(a.getRestaurant() != null) {
            jgen.writeObject(a.getRestaurant());
        } else {
            jgen.writeObject(null);
        }
        jgen.writeEndObject();
    }
}
