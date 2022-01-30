package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fhtw.cartridgeScaping.cartridge.Cartridge;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Door;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.text.ItemDescription;
import fhtw.cartridgeScaping.gameplay.text.RoomDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CartridgeDeserializer extends StdDeserializer<Cartridge> {

    public CartridgeDeserializer() {
        this(null);
    }

    protected CartridgeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Cartridge deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        /* INFO gameInformation */
        JsonNode node = p.getCodec().readTree(p);
        String filename = node.get("filename").textValue();
        String gameTitle = node.get("gameTitle").textValue();
        // TODO Parse field gameImage
        int sizeX = node.get("map").get("size").get("sizeX").intValue();
        int sizeY = node.get("map").get("size").get("sizeY").intValue();
        Pair<Integer, Integer> spawn = new Pair<>(
                node.get("spawn").get("X").intValue(),
                node.get("spawn").get("Y").intValue());

        // Local storage of objects for bi-directional references
        HashMap<String, GameObject> objects = new HashMap<>();

        Iterator<String> roomEntries = node.get("rooms").fieldNames(); /* INFO Parse rooms */
        HashMap<Integer, Room> rooms = new HashMap<>();
        System.out.println("Parsing rooms...");
        while(roomEntries.hasNext()) {
            String id = roomEntries.next();
            JsonNode roomNode = node.get("rooms").get(id);
            Room room = new Room(ctxt.readTreeAsValue(
                                    roomNode.get("roomDescription"), RoomDescription.class).cloneDescription());
            room.setOriginalID(id);
            room.setLocation(new Pair<>(
                    roomNode.get("location").get("key").intValue(),
                    roomNode.get("location").get("value").intValue()));
            objects.put(id, room);
            rooms.put(Integer.parseInt(room.getOriginalID()), room);
            System.out.printf("Created object '%s'\n", id);
        }

        Iterator<String> doorEntries = node.get("doors").fieldNames(); /* INFO Parse doors */
        HashMap<Integer, Door> doors = new HashMap<>();
        System.out.println("Parsing doors...");
        while(doorEntries.hasNext()) {
            String id = doorEntries.next();
            JsonNode doorNode = node.get("doors").get(id);
            Door door = new Door(
                    ctxt.readTreeAsValue(doorNode.get("doorDescription"), DoorDescription.class).cloneDescription(),
                    doorNode.get("isLocked").booleanValue(),
                    doorNode.get("isOpen").booleanValue());
            door.setOriginalID(id);
            objects.put(id, door);
            doors.put(Integer.parseInt(door.getOriginalID()), door);
            System.out.printf("Created object '%s'\n", id);
        }

        Iterator<String> itemEntries = node.get("items").fieldNames(); /* INFO Parse items */
        HashMap<Integer, Item> items = new HashMap<>();
        System.out.println("Parsing items...");
        while(itemEntries.hasNext()) {
            String id = itemEntries.next();
            JsonNode itemNode = node.get("items").get(id);
            Item item = new Item(itemNode.get("canBeHeld").asBoolean(), ctxt.readTreeAsValue(
                    itemNode.get("itemDescription"), ItemDescription.class).cloneDescription());
            item.setOriginalID(id);
            objects.put(id, item);
            items.put(Integer.parseInt(item.getOriginalID()), item);
            System.out.printf("Created object '%s'\n", id);
        }

        /* INFO Create bi-directional object references */
        for (Map.Entry<String, GameObject> entry :
                objects.entrySet()) {
            if(entry.getValue() instanceof Room room) {
                JsonNode current = node.get("rooms").get(entry.getKey());
                ArrayList doorIds = ctxt.readTreeAsValue(current.get("doors"), ArrayList.class);
                for (Object id :
                        doorIds) {
                    room.addDoor((Door) objects.get(String.valueOf(id)));
                }
                Iterator<String> dirEntries = current.get("directions").fieldNames();
                while(dirEntries.hasNext()) {
                    String direction = dirEntries.next();
                    int id = current.get("directions").get(direction).intValue();
                    room.addDirection(Direction.getDirection(direction),
                            (Room) objects.get(String.valueOf(id)));
                }
                ArrayList itemIds = ctxt.readTreeAsValue(current.get("items"), ArrayList.class);
                for (Object id :
                        itemIds) {
                    room.addItem(objects.get(String.valueOf(id)));
                }
            }
            if(entry.getValue() instanceof Door door) {
                JsonNode current = node.get("doors").get(entry.getKey());
                door.setRooms(
                        (Room)objects.get(String.valueOf(current.get("source").intValue())),
                        (Room)objects.get(String.valueOf(current.get("destination").intValue())));
            }
        }

        return new Cartridge(
                filename,
                gameTitle,
                sizeX, sizeY,
                spawn,
                new ArrayList<>(rooms.values()),
                new ArrayList<>(doors.values()),
                new ArrayList<>(items.values())
        );
    }
}
