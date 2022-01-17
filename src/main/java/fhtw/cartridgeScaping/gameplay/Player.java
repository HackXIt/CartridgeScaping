package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.console.CommandManager;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.PlayerDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;

import java.util.HashMap;

public class Player implements Lookable, Inspectable {
    private final PlayerDescription playerDescription;
    private final HashMap<Integer, Item> inventory;
    private Room currentRoom;
    private final CommandManager commandManager;

    public Player(PlayerDescription playerDescription) {
        this.playerDescription = playerDescription;
        this.inventory = new HashMap<>();
        this.commandManager = new CommandManager(this);
    }

    // NOTE Kept for later when there's possible cache of playerInventory
//    public Player(String playerName, HashMap<Integer, Item> inventory) {
//        this.playerName = playerName;
//        this.inventory = inventory;
//    }

    public String getPlayerName() {
        return playerDescription.getName();
    }

    public void setPlayerName(String playerName) {
        playerDescription.setName(playerName);
    }

    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addItem(GameObject item) {
        inventory.put(item.hashCode(), (Item) item);
    }

    public void pickupItem(Item item) {
        if(!inventory.containsKey(item.hashCode())) {
            item.pickup(currentRoom, this);
        } else {
            // TODO PlayerMessage notifying the item is already in their possession.
        }
    }

    public void dropItem(Item item) {
        if(inventory.containsKey(item.hashCode())) {
            item.drop(currentRoom);
        } else {
            // TODO PlayerMessage notifying that the item isn't in their possession.
        }
    }

    private void teleport(Room room) {
        currentRoom.removePlayer(this);
        currentRoom = room;
        currentRoom.addPlayer(this);
    }

    public void walk(Direction direction) {
        if(currentRoom.getDirections().containsKey(direction)) {
            this.teleport(currentRoom.getDirections().get(direction));
        } else {
            // TODO PlayerMessage notifying that the given direction is not available in currentRoom
        }
    }

    public void look() {
        if(ViewManager.isDeveloperMode()) {
            System.out.println(currentRoom.lookAt());
        }
        // TODO LocalMessage upon looking at currentRoom
    }

    public void lookObject(GameObject object) {
        if(ViewManager.isDeveloperMode()) {
            System.out.println(object.lookAt());
        }
        // TODO LocalMessage upon looking at object
        // TODO PlayerMessage upon looking at Player
    }

    @Override
    public String inspect() {
        // TODO Return inspectable text from the playerDescription
        return null;
    }

    @Override
    public String lookAt() {
        return playerDescription.toString();
    }
}
