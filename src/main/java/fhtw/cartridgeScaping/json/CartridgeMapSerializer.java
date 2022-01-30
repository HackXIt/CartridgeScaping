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

public class CartridgeMapSerializer extends StdSerializer<HashMap<Room, Pair<Integer, Integer>>> {

    public CartridgeMapSerializer() {
        this(null);
    }

    public CartridgeMapSerializer(Class<HashMap<Room, Pair<Integer, Integer>>> t) {
        super(t);
    }

    @Override
    public void serialize(HashMap<Room, Pair<Integer, Integer>> value,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        HashMap<Integer, Pair<Integer, Integer>> map = new HashMap<>();
        gen.writeStartObject();
        // Create new map based on IDs
        for (Room room :
                value.keySet()) {
            gen.writeObjectFieldStart(String.valueOf(room.hashCode()));
            gen.writeNumberField("X", value.get(room).getKey());
            gen.writeNumberField("Y", value.get(room).getValue());
            gen.writeEndObject(); // for room-id
            map.put(room.hashCode(), value.get(room));
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
