package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.util.Pair;

import java.io.IOException;

public class CartridgeSpawnSerializer extends StdSerializer<Pair<Integer, Integer>> {

    public CartridgeSpawnSerializer() {
        this(null);
    }

    protected CartridgeSpawnSerializer(Class<Pair<Integer, Integer>> t) {
        super(t);
    }

    @Override
    public void serialize(Pair<Integer, Integer> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("X", value.getKey());
        gen.writeNumberField("Y", value.getValue());
        gen.writeEndObject();
    }
}
