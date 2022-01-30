package fhtw.cartridgeScaping.gameplay.rooms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fhtw.cartridgeScaping.controller.ViewManager;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.text.RoomDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import fhtw.cartridgeScaping.json.RoomDirectionSerializer;
import fhtw.cartridgeScaping.json.PairDeserializer;
import fhtw.cartridgeScaping.json.RoomObjectSerializer;
import fhtw.cartridgeScaping.networking.NetworkManager;
import javafx.util.Pair;

import java.util.HashMap;

public class Room extends GameObject<Room> {
    private RoomDescription roomDescription;
    @JsonSerialize(using = RoomObjectSerializer.class)
    private HashMap<Integer, Door> doors;
    @JsonSerialize(using = RoomDirectionSerializer.class)
    private HashMap<Direction, Room> directions;
    @JsonSerialize(using = RoomObjectSerializer.class)
    private HashMap<Integer, Item> items;
    @JsonIgnore
    private final HashMap<Integer, Player> players;
    @JsonDeserialize(using = PairDeserializer.class)
    private Pair<Integer, Integer> location;
    private boolean hasDoors;
    private boolean hasItems;

//    NOTE Constructors ----------------------------------------

    @JsonCreator
    public Room(@JsonProperty("roomDescription") RoomDescription roomDescription) {
        super(false); // Rooms can never be held.
        this.roomDescription = roomDescription.cloneDescription();
        this.directions = new HashMap<>();
        this.doors = new HashMap<>();
        this.items = new HashMap<>();
        this.players = new HashMap<>();
    }

    public Room(RoomDescription roomDescription,
                HashMap<Direction, Room> directions) {
        this(roomDescription);
        this.directions = directions;
    }

    public Room(RoomDescription roomDescription,
                HashMap<Direction, Room> directions,
                HashMap<Integer, Door> doors) {
        this(roomDescription, directions);
        this.doors = doors;
        this.hasDoors = true;
    }

    public Room(RoomDescription roomDescription,
                HashMap<Direction, Room> directions,
                HashMap<Integer, Door> doors,
                HashMap<Integer, Item> items) {
        this(roomDescription, directions, doors);
        this.items = items;
        this.hasItems = true;
    }

    public Room(Room room, String originalID) {
        super(room, originalID);
        this.roomDescription = room.getRoomDescription().cloneDescription();
        this.directions = room.getDirections();
        this.items = room.hasItems() ? room.getItems() : new HashMap<>();
        this.doors = room.hasDoors() ? room.getDoors() : new HashMap<>();
        this.hasDoors = room.hasDoors();
        this.hasItems = room.hasItems();
        this.players = new HashMap<>();
    }

//    NOTE Member Methods ----------------------------------------

    public RoomDescription getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(RoomDescription roomDescription) {
        this.roomDescription = roomDescription;
    }

    public HashMap<Integer, Door> getDoors() {
        return doors;
    }

    public void setDoors(HashMap<Integer, Door> doors) {
        if(doors != null) {
            this.doors = doors;
        }
    }

    public HashMap<Direction, Room> getDirections() {
        return directions;
    }

    public void setDirections(HashMap<Direction, Room> directions) {
        if(directions != null) {
            this.directions = directions;
        }
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, Item> items) {
        if(items != null) {
            this.items = items;
        }
    }

    public Pair<Integer, Integer> getLocation() {
        return location;
    }

    public void setLocation(Pair<Integer, Integer> location) {
        this.location = location;
    }

    public boolean hasDoors() {
        return hasDoors;
    }

    public boolean hasItems() {
        return hasItems;
    }

    public HashMap<Integer, Player> getPlayers() {
        return players;
    }

    //    NOTE Utility Methods ----------------------------------------

    /* FIXME ALL Utility Methods of Room really need to reworked or checked!
    The implementation of the Room utility methods needs to be thoroughly checked,
    because there's a lot that can go wrong.
    This was one of the earliest classes, even before GameObject class existed,
    and the methods implemented here to manipulate the objects in the room,
    were generally implemented too early and may contain errors!
     */

    public boolean addItem(GameObject object) {
        try {
            items.put(object.hashCode(), (Item) object);
            // TODO RoomMessage upon successfully executing addItem
            ViewManager.getInstance().devLog(String.format(
                    "Object '%s' was dropped in '%s'.", object.getName(), roomDescription.getName()
            ));
            hasItems = true;
            return true;
        } catch (Exception e) {
            ViewManager.getInstance().errorLog(
                    String.format("Failed to add item to %s", roomDescription.getName()), e);
            return false;
        }
    }

    public boolean removeItem(GameObject object) {
        try {
            items.remove(object.hashCode());
            // TODO RoomMessage upon successfully executing removeItem
            ViewManager.getInstance().devLog(
                    String.format("%s was removed from %s.", object.getName(), roomDescription.getName()));
            if(items.isEmpty()) {
                hasItems = false;
            }
            return true;
        } catch (Exception e) {
            ViewManager.getInstance().errorLog(
                    String.format("Failed to remove item from %s", roomDescription.getName()), e
            );
            return false;
        }
    }

    public boolean addPlayer(Player player) {
        try {
            players.put(player.hashCode(), player);
            // TODO RoomMessage upon player entering room (Prints arrival of player to other players
            // TODO PlayerMessage upon player entering room (Prints room description to Player)
            ViewManager.getInstance().devLog(
                    String.format("'%s' entered '%s'.",
                            player.getName(), roomDescription.getName()));
            return true;
        } catch (Exception e) {
            ViewManager.getInstance().errorLog(
                    String.format("Failed to add '%s' to '%s'.",
                            player.getName(), roomDescription.getName()), e
            );
            return false;
        }
    }

    public boolean removePlayer(Player player) {
        try {
            players.remove(player.hashCode());
            // TODO RoomMessage upon player leaving room (Prints departure of player to other players)
            // TODO PlayerMessage upon player leaving room (Prints leave message to Player)
            ViewManager.getInstance().devLog(String.format(
                    "'%s' has left '%s'.",
                    player.getName(), roomDescription.getName()
            ));
            return true;
        } catch (Exception e) {
            ViewManager.getInstance().errorLog(
                    String.format("Failed to remove '%s' from '%s'.",
                            player.getName(), roomDescription.getName()), e
            );
            return false;
        }
    }

    public boolean addDoor(Door door) {
        // FIXME Adding doors to Room currently doesn't edit Door object (Necessary for inside/outside)
        if(door != null && !doors.containsKey(door.hashCode())) {
            doors.put(door.hashCode(), door);
            System.out.printf("'%s' was added to '%s'.\n",
                    door.getDoorDescription().getName(),
                    roomDescription.getName());
            hasDoors = true;
            return true;
        } else {
            System.err.printf("'%s' already exists or object was 'null'.", door.getDoorDescription().getName());
            return false;
        }
    }

    public boolean removeDoor(Door door) {
        if(door != null && doors.containsKey(door.hashCode())) {
            doors.remove(door.hashCode());
            System.out.printf("'%s' was removed from '%s'.\n",
                    door.getDoorDescription().getName(),
                    roomDescription.getName());
            if(doors.isEmpty()) {
                hasDoors = false;
            }
            return true;
        } else {
            System.err.printf("'%s' not in room or object was 'null'.", door.getDoorDescription().getName());
            return false;
        }
    }

    // DONE Currently adding directions doesn't consider adding the opposite direction to the target room
    public boolean addDirection(Direction direction, Room target) {
        if(direction == null || target == null) {
            ViewManager.getInstance().errorLog(
                    "Given Direction or Target object is 'null'.",
                    new NullPointerException("Direction or Target object is 'null'.")
            );
            return false;
        }
        if(target.getDirections().containsKey(direction.getOpposite())) {
            ViewManager.getInstance().errorLog(String.format(
                    "Cannot add '%s' to %s - The opposite direction '%s' already exists in %s.",
                    direction.getDirName(),
                    this.getName(),
                    direction.getOpposite().getDirName(),
                    target.getName()
            ), new IllegalArgumentException("Cannot add same direction to room twice."));
            return false;
        }
        if(directions.containsKey(direction)) {
            ViewManager.getInstance().errorLog(String.format(
                    "Cannot add '%s' to %s - The direction '%s' already exists in %s.",
                    direction.getDirName(),
                    this.getName(),
                    direction.getDirName(),
                    this.getName()
            ), new IllegalArgumentException("Cannot add same direction to room twice."));
            return false;
        }
        directions.put(direction, target);
        target.getDirections().put(direction.getOpposite(), this);
        ViewManager.getInstance().devLog(String.format(
                "Added '%s' to Room '%s', which leads to '%s'.",
                direction.getDirName(),
                this.getName(),
                target.getName()
        ));
        return true;
    }

    // DONE Currently removing directions doesn't consider removing the opposite direction from target room
    public boolean removeDirection(Direction direction) {
        if(direction == null) {
            ViewManager.getInstance().errorLog(
                    "Given Direction is null.",
                    new NullPointerException("Direction is null."));
            return false;
        }
        if(!directions.containsKey(direction)) {
            ViewManager.getInstance().errorLog(String.format(
                    "The direction '%s' doesn't exist in '%s'.",
                    direction.getDirName(),
                    this.getName()
            ), new IllegalArgumentException("Direction doesn't exist in room."));
            return false;
        }
        // NOTE No need to check target room, since the existence of the direction proves existence of opposite
        directions.get(direction).getDirections().remove(direction.getOpposite()); // Remove opposite from target room
        directions.remove(direction); // Remove direction from this room
        ViewManager.getInstance().devLog(String.format(
                "Direction '%s' removed from '%s'.",
                direction.getDirName(),
                this.getName()
        ));
        return true;
    }



    @Override
    public String toString() {
        /**
         * NOTE Room-Example of toString()
         * Cellar - (A dark cellar under the basement)
         * The lighting of this cellar is very dim, if noticeable at all. The walls are painted clinically white.
         * A <ring> has been placed on the floor here. A piece of <paper> resides on a table.
         *
         * There is a <wooden door> leading back to where you came from. You can go <north, east> from here.
         */
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(roomDescription.toString());
        items.forEach((k, v) -> {
            strBuilder.append(v.getPlacedDescription());
        });
        doors.forEach((k,v) -> {
            DoorDescription doorDesc = v.getDoorDescription();
            strBuilder.append(doorDesc.isInside() ?
                    doorDesc.getInsideDescription() :
                    doorDesc.getOutsideDescription());
        });
        directions.forEach((k,v) -> {
            strBuilder.append(k.getDirDesc());
        });
        return strBuilder.toString();
    }

    @Override
    public String lookAt() {
        return toString();
    }

    @Override
    public String inspect() {
        return null;
    }

    @Override
    public String getName() {
        return roomDescription.getName();
    }

    /* NOTE Cloning rooms is only allowed for Host because of original reference to objectID */
    @Override
    public Room cloneObject() {
        if(NetworkManager.getInstance().isHost()) {
            return new Room(this, String.valueOf(hashCode()));
        }
        return null;
    }
}
