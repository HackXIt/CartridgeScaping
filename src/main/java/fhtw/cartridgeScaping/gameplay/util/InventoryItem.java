package fhtw.cartridgeScaping.gameplay.util;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.rooms.Room;

public interface InventoryItem {
    void pickup(Room currentRoom, Player player);
    void drop(Room room);
}
