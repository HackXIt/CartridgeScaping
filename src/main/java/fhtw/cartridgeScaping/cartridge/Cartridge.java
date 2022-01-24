package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.items.Secret;
import javafx.scene.image.Image;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Vector;

// Data structure for cartridge
public class Cartridge {
    private String fileName;
    private Path filePath;
    private String gameTitle;
    private Image gameImage;
    private Vector<Vector<Room>> rooms;
    private HashMap<Integer, Item> items;
    private HashMap<Integer, Secret> secrets;
    private int spawnX = 0;
    private int spawnY = 0;

    public Cartridge() {
    }
}
