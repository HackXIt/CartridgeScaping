package fhtw.cartridgeScaping.cartridge;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Door;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.items.Secret;
import fhtw.cartridgeScaping.json.CartridgeMapSerializer;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.HashMap;

// Data structure for cartridge
public class Cartridge {
    private String filename;
    private String gameTitle;
    private Image gameImage;
    private Pair<Integer, Integer> spawn;
    private int sizeX;
    private int sizeY;
    @JsonSerialize(using = CartridgeMapSerializer.class)
    private HashMap<Room, Pair<Integer, Integer>> map;
    private HashMap<Integer, Item> items;
    private HashMap<Integer, Room> rooms;
    private HashMap<Integer, Door> doors;
    private HashMap<Integer, Secret> secrets;

    public Cartridge() {
        spawn = new Pair<>(0, 0);
        items = new HashMap<>();
        rooms = new HashMap<>();
        doors = new HashMap<>();
        secrets = new HashMap<>();
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
        if(spawn.getKey() >= 0 && spawn.getValue() >= 0) {
            if(spawn.getKey() <= sizeX && spawn.getValue() <= sizeY) {
                this.spawn = spawn;
            } else {
                throw new IllegalArgumentException("Spawn must be within grid-range.");
            }
        } else {
            throw new IllegalArgumentException("Spawn must be a non-negative number.");
        }
    }

    public Cartridge(String filename,
                     String gameTitle,
                     Image gameImage,
                     int sizeX, int sizeY) {
        this(filename, gameTitle, sizeX, sizeY);
        this.gameImage = gameImage;
    }

    public Cartridge(String filename,
                     String gameTitle,
                     Image gameImage,
                     int sizeX, int sizeY,
                     Pair<Integer, Integer> spawn) {
        this(filename, gameTitle, sizeX, sizeY, spawn);
        this.gameImage = gameImage;
    }

    public void addItem(Item item) {
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
        if(map.containsKey(room)) {
            throw new IllegalArgumentException("Map already contains room. Rooms can't exist twice.");
        }
        room.setLocation(new Pair<>(x, y));
        map.put(room, room.getLocation());
        rooms.put(room.hashCode(), room);
    }

    public void addDoor(Door door) {
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

    public HashMap<Room, Pair<Integer, Integer>> getMap() {
        return map;
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
