package fhtw.cartridgeScaping.gameplay;

import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.console.CommandManager;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.PlayerDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import fhtw.cartridgeScaping.gameplay.util.Holdable;
import fhtw.cartridgeScaping.gameplay.util.Inspectable;
import fhtw.cartridgeScaping.gameplay.util.Lookable;

import java.io.Serializable;
import java.util.HashMap;

/* FIXME The inventory needs to be reworked
Using GameObject for inventory might not be a very good idea.
I don't want players to accidentally end up with Doors or Rooms inside their pockets.
Using Holdable would be the better approach design-wise.
 */

public class Player implements Lookable, Inspectable, Serializable {
    private static Player singleton_instance;
    private final PlayerDescription playerDescription;
    private int currentRoomId = 0;
    private transient Room currentRoom;
    private transient final HashMap<Integer, GameObject> inventory;
    private transient final CommandManager commandManager;

    private Player() {
        playerDescription = new PlayerDescription();
        inventory = new HashMap<>();
        commandManager = new CommandManager();
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

    public HashMap<Integer, GameObject> getInventory() {
        return inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoomId = Integer.parseInt(currentRoom.getOriginalID());
        this.currentRoom = currentRoom;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void addItem(GameObject item) {
        inventory.put(item.hashCode(), (Item) item);
    }

    public void pickupObject(Holdable object) {
        // TODO Add text-response for picking up an object
        if(!inventory.containsKey(object.hashCode())) {
            object.pickup();
        } else {
            // TODO PlayerMessage notifying the object is already in their possession.
        }
    }

    public void dropObject(Holdable object) {
        // TODO Add text-response for dropping an object
        if(inventory.containsKey(object.hashCode())) {
            object.drop();
        } else {
            // TODO PlayerMessage notifying that the object isn't in their possession.
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

    public void lookObject(Lookable object) {
        ViewManager.getInstance().devLog(
                String.format("%s looks at %s.", getName(), object.getName())
        );
        // DONE Implement lookObject in Player
        // TODO Send LocalMessage upon looking at object
        // TODO Send PlayerMessage upon looking at Player
        ViewManager.getInstance().getCurrentOutputArea().appendText(
                String.format("You look at %s.", object.getName())
        );
        ViewManager.getInstance().getCurrentOutputArea().appendText(object.toString());
    }

    @Override
    public String inspect() {
        return playerDescription.getDetailedDescription();
    }

    @Override
    public String lookAt() {
        return playerDescription.toString();
    }
}
