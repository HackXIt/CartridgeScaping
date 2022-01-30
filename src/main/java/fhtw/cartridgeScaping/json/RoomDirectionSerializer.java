package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.util.Direction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RoomDirectionSerializer extends StdSerializer<HashMap<Direction, Room>> {

    public RoomDirectionSerializer() {
        this(null);
    }

    public RoomDirectionSerializer(Class<HashMap<Direction, Room>> t) {
        super(t);
    }

    @Override
    public void serialize(HashMap<Direction, Room> value,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        HashMap<Direction, Integer> directions = new HashMap<>();
        for (Map.Entry<Direction, Room> entry :
                value.entrySet()) {
            directions.put(entry.getKey(), entry.getValue().hashCode());
        }
        gen.writeObject(directions);
    }
}
