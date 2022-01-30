package fhtw.cartridgeScaping.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fhtw.cartridgeScaping.gameplay.rooms.Door;

import java.io.IOException;

public class DoorSerializer extends StdSerializer<Door> {

    public DoorSerializer() {
        this(null);
    }

    protected DoorSerializer(Class<Door> t) {
        super(t);
    }

    @Override
    public void serialize(Door value,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        gen.writeStartObject(value.hashCode(), 5);
        gen.writeObjectField("doorDescription", value.getDoorDescription());
        gen.writeObjectField("source", value.getRoomSource().hashCode());
        gen.writeObjectField("destination", value.getRoomDestination().hashCode());
        gen.writeObjectField("isLocked", value.isLocked());
        gen.writeObjectField("isOpen", value.isOpen());
        gen.writeEndObject();
    }
}
