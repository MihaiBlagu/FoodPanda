package FoodPandaBack.serializer;

import FoodPandaBack.model.Customer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomerSerializer extends StdSerializer<Customer> {
    public CustomerSerializer() {
        this(null);
    }

    public CustomerSerializer(Class<Customer> c) {
        super(c);
    }

    @Override
    public void serialize(Customer c, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("userId", c.getUserId());
        jgen.writeStringField("username", c.getUsername());
        jgen.writeStringField("password", c.getPassword());
        jgen.writeEndObject();
    }
}
