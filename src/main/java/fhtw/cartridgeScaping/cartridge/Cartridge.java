package fhtw.cartridgeScaping.cartridge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Door;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.items.Secret;
import fhtw.cartridgeScaping.json.*;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

// Data structure for cartridge
@JsonDeserialize(using = CartridgeDeserializer.class)
public class Cartridge {
    private String filename;
    private String gameTitle;
    @JsonIgnore
    private Image gameImage;
    @JsonSerialize(using = CartridgeSpawnSerializer.class)
    private Pair<Integer, Integer> spawn;
    private int sizeX;
    private int sizeY;
    @JsonSerialize(using = CartridgeMapSerializer.class)
    private HashMap<Pair<Integer, Integer>, Room> map;
    @JsonIgnore
    private HashMap<String, Integer> objects;
    private HashMap<Integer, Room> rooms;
    private HashMap<Integer, Door> doors;
    private HashMap<Integer, Item> items;
    private HashMap<Integer, Secret> secrets;
    private HashMap<String, Integer> originalIdReferences;
    private HashMap<Integer, String> objectTypeReferences;

    public Cartridge() {
        spawn = new Pair<>(0, 0);
        items = new HashMap<>();
        rooms = new HashMap<>();
        doors = new HashMap<>();
        secrets = new HashMap<>();
        objects = new HashMap<>();
        objectTypeReferences = new HashMap<>();
        originalIdReferences = new HashMap<>();
    }

    public Cartridge(String filename,
                     String gameTitle,
                     int sizeX, int sizeY) throws IllegalArgumentException{
        this();
        this.filename = filename;
        this.gameTitle = gameTitle;
        if(sizeX > 0 && sizeY > 0) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            map = new HashMap<>();
        } else {
            throw new IllegalArgumentException("Grid sizes must be greater than 0.");
        }
    }

    public Cartridge(String filename,
                     String gameTitle,
                     int sizeX, int sizeY,
                     Pair<Integer, Integer> spawn) throws IllegalArgumentException {
        this(filename, gameTitle, sizeX, sizeY);
        setSpawn(spawn);
    }

    public Cartridge(String filename,
                     String gameTitle,
                     int sizeX, int sizeY,
                     Pair<Integer, Integer> spawn,
                     ArrayList<Room> rooms,
                     ArrayList<Door> doors,
                     ArrayList<Item> items) throws IllegalArgumentException {
        this(filename, gameTitle, sizeX, sizeY, spawn);
        for (Room room :
                rooms) {
            addRoom(room.getLocation().getKey(), room.getLocation().getValue(), room);
        }
        for (Door door :
                doors) {
            addDoor(door);
        }
        for (Item item :
                items) {
            addItem(item);
        }
    }

    public Cartridge(String filename,
                     String gameTitle,
                     Image gameImage,
                     int sizeX, int sizeY) throws IllegalArgumentException {
        this(filename, gameTitle, sizeX, sizeY);
        this.gameImage = gameImage;
    }

    public Cartridge(String filename,
                     String gameTitle,
                     Image gameImage,
                     int sizeX, int sizeY,
                     Pair<Integer, Integer> spawn) throws IllegalArgumentException {
        this(filename, gameTitle, sizeX, sizeY, spawn);
        this.gameImage = gameImage;
    }

    private void addGameObject(GameObject object) throws IllegalArgumentException {
        if(objectTypeReferences.containsKey(object.hashCode())) {
            throw new IllegalArgumentException("Object already exists, cannot add object twice.");
        }
//        if(originalIdReferences.containsKey(object.getOriginalID())) {
//            throw new IllegalArgumentException("Original object already exists, cannot add object twice.");
//        }
        objects.put(object.getName(), object.hashCode());
        originalIdReferences.put(object.getOriginalID(), object.hashCode());
        objectTypeReferences.put(object.hashCode(), object.getObjectTypeReference());
    }

    public void addItem(Item item) throws IllegalArgumentException, NullPointerException {
        if(item == null) {
            throw new NullPointerException("Cannot add 'null' item.");
        }
        addGameObject(item);
        items.put(item.hashCode(), item);
    }

    // FIXME Currently it is possible to have two rooms at the same spot in the map!
    public void addRoom(int x, int y, Room room) throws IllegalArgumentException, NullPointerException {
        if(room == null) {
            throw new NullPointerException("Cannot add 'null' room.");
        }
        if(x > sizeX || y > sizeY || x < 0 || y < 0) {
            throw new IllegalArgumentException("Room must be within size of map.");
        }
        addGameObject(room);
        if(room.getLocation() == null) {
            room.setLocation(new Pair<>(x, y));
        }
        map.put(room.getLocation(), room);
        rooms.put(room.hashCode(), room);
    }

    public void addDoor(Door door) throws IllegalArgumentException, NullPointerException {
        if(door == null) {
            throw new NullPointerException("Cannot add 'null' door.");
        }
        addGameObject(door);
        doors.put(door.hashCode(), door);
    }

    public String getFilename() {
        return filename;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public Image getGameImage() {
        return gameImage;
    }

    public Pair<Integer, Integer> getSpawn() {
        return spawn;
    }

    public void setSpawn(Pair<Integer, Integer> spawn) throws IllegalArgumentException {
        if(spawn.getKey() < 0 || spawn.getValue() < 0) {
            throw new IllegalArgumentException("Spawn must be a non-negative number.");
        }
        if(spawn.getKey() > sizeX || spawn.getValue() > sizeY) {
            throw new IllegalArgumentException("Spawn must be within grid-range.");
        }
        this.spawn = spawn;
    }

    public HashMap<Pair<Integer, Integer>, Room> getMap() {
        return map;
    }

    public HashMap<String, Integer> getObjects() {
        return objects;
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public HashMap<Integer, Door> getDoors() {
        return doors;
    }

    public HashMap<Integer, Secret> getSecrets() {
        return secrets;
    }
}
