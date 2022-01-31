package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CartridgeMapSerializer extends StdSerializer<HashMap<Pair<Integer, Integer>, Room>> {

    public CartridgeMapSerializer() {
        this(null);
    }

    public CartridgeMapSerializer(Class<HashMap<Pair<Integer, Integer>, Room>> t) {
        super(t);
    }

    @Override
    public void serialize(HashMap<Pair<Integer, Integer>, Room> value,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        HashMap<Integer, Pair<Integer, Integer>> map = new HashMap<>();
        gen.writeStartObject();
        // Create new map based on IDs
        for (Map.Entry<Pair<Integer, Integer>, Room> entry :
                value.entrySet()) {
            int x = entry.getKey().getKey();
            int y = entry.getKey().getValue();
            int hash = entry.getValue().hashCode();
            gen.writeObjectFieldStart(String.valueOf(entry.getValue().hashCode()));
            gen.writeNumberField("X", x);
            gen.writeNumberField("Y", y);
            gen.writeEndObject(); // for room-id
            map.put(hash, entry.getKey());
        }
        // Find map size based on rooms
        int sizeX = Collections.max(map.values(), Comparator.comparing(Pair::getKey)).getKey();
        int sizeY = Collections.max(map.values(), Comparator.comparing(Pair::getValue)).getValue();
        gen.writeObjectFieldStart("size");
        gen.writeNumberField("sizeX", sizeX);
        gen.writeNumberField("sizeY", sizeY);
        gen.writeEndObject(); // For mapSize
        gen.writeEndObject(); // For map
    }
}
