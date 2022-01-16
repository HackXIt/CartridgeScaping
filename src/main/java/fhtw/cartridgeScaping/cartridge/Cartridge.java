package fhtw.cartridgeScaping.cartridge;

import fhtw.cartridgeScaping.gameplay.Item;
import fhtw.cartridgeScaping.gameplay.Room;
import fhtw.cartridgeScaping.gameplay.Secret;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

// Data structure for cartridge
public class Cartridge {
    private String fileName;
    private Path filePath;
    private Vector<Vector<Room>> rooms;
    private LinkedList<Item> items;
    private LinkedList<Secret> secrets;
    private int spawnX = 0;
    private int spawnY = 0;

    public Cartridge(String fileName,
                     Path filePath,
                     Vector<Vector<Room>> rooms,
                     LinkedList<Item> items,
                     LinkedList<Secret> secrets,
                     int spawnX,
                     int spawnY) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.rooms = rooms;
        this.items = items;
        this.secrets = secrets;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }
}
