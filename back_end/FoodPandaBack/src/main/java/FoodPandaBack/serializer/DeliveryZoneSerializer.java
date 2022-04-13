package FoodPandaBack.serializer;

import FoodPandaBack.model.DeliveryZone;
import FoodPandaBack.model.MenuItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DeliveryZoneSerializer extends StdSerializer<DeliveryZone> {
    public DeliveryZoneSerializer() {
        this(null);
    }

    public DeliveryZoneSerializer(Class<DeliveryZone> d) {
        super(d);
    }

    @Override
    public void serialize(DeliveryZone d, JsonGenerator jgen,
                          SerializerProvider serializerProvider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("zoneId", d.getZoneId());
        jgen.writeStringField("name", d.getName());

        jgen.writeEndObject();
    }
}
