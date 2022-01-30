package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.items.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomObjectSerializer extends StdSerializer<HashMap<Integer, GameObject>> {

    public RoomObjectSerializer() {
        this(null);
    }

    public RoomObjectSerializer(Class<HashMap<Integer, GameObject>> t) {
        super(t);
    }

    @Override
    public void serialize(HashMap<Integer, GameObject> value,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        ArrayList<Integer> objects = new ArrayList<>();
        for (GameObject object:
             value.values()) {
            objects.add(object.hashCode());
        }
        gen.writeObject(objects);
    }
}
