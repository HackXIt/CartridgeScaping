package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.gameplay.console.CommandManager;
import fhtw.cartridgeScaping.gameplay.console.HelpManager;
import fhtw.cartridgeScaping.gameplay.console.HelpText;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.function.Consumer;

public class Player {
    private String playerName;
    private final HashMap<Integer, Item> inventory;
    private Room currentRoom;
    private final HashMap<String, Pair<Consumer<Item>, HelpText>> possibleItemInteractions = new HashMap<>();
    private final CommandManager commandManager;

    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory = new HashMap<>();
        possibleItemInteractions.put("take",
                new Pair<>(this::pickupItem, HelpManager.getHelpText("take")));
        possibleItemInteractions.put("drop",
                new Pair<>(this::pickupItem, HelpManager.getHelpText("drop")));
        this.commandManager = new CommandManager(this);
    }

    // NOTE Kept for later when there's possible cache of playerInventory
//    public Player(String playerName, HashMap<Integer, Item> inventory) {
//        this.playerName = playerName;
//        this.inventory = inventory;
//    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public HashMap<String, Pair<Consumer<Item>, HelpText>> getPossibleItemInteractions() {
        return possibleItemInteractions;
    }

    public void addItem(Item item) {
        inventory.put(item.getId(), item);
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
}
