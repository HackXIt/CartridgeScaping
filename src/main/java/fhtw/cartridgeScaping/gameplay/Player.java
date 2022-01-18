package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.console.CommandManager;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.PlayerDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;

import java.io.Serializable;
import java.util.HashMap;

public class Player implements Lookable, Inspectable, Serializable {
    private static Player singleton_instance;
    private final PlayerDescription playerDescription = new PlayerDescription();
    private final int currentRoomId = 0;
    private transient Room currentRoom;
    private transient final HashMap<Integer, Item> inventory = new HashMap<>();
    private transient final CommandManager commandManager = new CommandManager();

    private Player() {

    }

    public static Player getInstance() {
        if(singleton_instance == null) {
            singleton_instance = new Player();
        }
        return singleton_instance;
    }

    public String getName() {
        return playerDescription.getName();
    }

    public void setName(String playerName) {
        playerDescription.setName(playerName);
    }

    public String getShortDescription() {return playerDescription.getShortDescription();}

    public void setShortDescription(String shortDescription) {
        playerDescription.setShortDescription(shortDescription);
    }

    public String getLongDescription() {
        return playerDescription.getLongDescription();
    }

    public void setLongDescription(String longDescription) {
        playerDescription.setLongDescription(longDescription);
    }

    public String getDetailedDescription() {
        return playerDescription.getDetailedDescription();
    }

    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void addItem(GameObject item) {
        inventory.put(item.hashCode(), (Item) item);
    }

    public void pickupItem(Item item) {
        if(!inventory.containsKey(item.hashCode())) {
            item.pickup();
        } else {
            // TODO PlayerMessage notifying the item is already in their possession.
        }
    }

    public void dropItem(Item item) {
        if(inventory.containsKey(item.hashCode())) {
            item.drop();
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
        ViewManager.getInstance().devLog(currentRoom.lookAt());
        // DONE Implement look in Player
        // TODO LocalMessage upon looking at currentRoom
        ViewManager.getInstance().getCurrentOutputArea().appendText(currentRoom.lookAt());
    }

    public void lookObject(GameObject object) {
        ViewManager.getInstance().devLog(
                String.format("%s looks at %s.", getName(), object.getString())
        );
        // DONE Implement lookObject in Player
        // TODO Send LocalMessage upon looking at object
        // TODO Send PlayerMessage upon looking at Player
        ViewManager.getInstance().getCurrentOutputArea().appendText(object.toString());
    }

    @Override
    public String inspect() {
        return getDetailedDescription();
    }

    @Override
    public String lookAt() {
        return playerDescription.toString();
    }
}
