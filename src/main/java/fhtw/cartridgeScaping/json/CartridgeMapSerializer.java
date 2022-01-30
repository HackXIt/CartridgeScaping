package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import javafx.util.Pair;

import java.io.IOException;
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
        for (Room room :
                value.keySet()) {
            map.put(room.hashCode(), value.get(room));
        }
        gen.writeObject(map);
    }
}
