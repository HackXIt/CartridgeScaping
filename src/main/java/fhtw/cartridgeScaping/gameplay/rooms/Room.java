package fhtw.cartridgeScaping.gameplay.rooms;

import fhtw.cartridgeScaping.gameplay.Player;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.text.RoomDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;

import java.util.HashMap;

public class Room {
    private RoomDescription roomDescription;
    private HashMap<Direction, Room> directions;
    private HashMap<String, Door> doors;
    private HashMap<String, Item> items;
    private HashMap<String, Player> players;

    private boolean hasDoors = false;
    private boolean hasItems = false;

//    NOTE Constructors ----------------------------------------

    public Room(RoomDescription roomDescription) {
        this.roomDescription = roomDescription;
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
                HashMap<String, Door> doors) {
        this(roomDescription, directions);
        this.doors = doors;
        this.hasDoors = true;
    }

    public Room(RoomDescription roomDescription,
                HashMap<Direction, Room> directions,
                HashMap<String, Door> doors,
                HashMap<String, Item> items) {
        this(roomDescription, directions, doors);
        this.items = items;
        this.hasItems = true;
    }

    public Room(Room room) {
        this.roomDescription = room.getRoomDescription();
        this.directions = room.getDirections();
        this.items = room.hasItems() ? room.getItems() : null;
        this.doors = room.hasDoors() ? room.getDoors() : null;
    }

//    NOTE Member Methods ----------------------------------------

    public RoomDescription getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(RoomDescription roomDescription) {
        this.roomDescription = roomDescription;
    }

    public HashMap<String, Door> getDoors() {
        return doors;
    }

    public void setDoors(HashMap<String, Door> doors) {
        this.doors = doors;
    }

    public HashMap<String, Door> copyDoors() {
        // TODO Implement deepCopy for copyDoors()
        return new HashMap<>(doors);
    }

    public HashMap<Direction, Room> getDirections() {
        return directions;
    }

    public void setDirections(HashMap<Direction, Room> directions) {
        this.directions = directions;
    }

    public HashMap<Direction, Room> copyDirections() {
        // TODO Implement deepCopy for copyDirections()
        return new HashMap<>(directions);
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    public HashMap<String, Item> copyItems() {
        // TODO Implement deepCopy for copyItems()
        return new HashMap<>(items);
    }

    public boolean hasDoors() {
        return hasDoors;
    }

    public boolean hasItems() {
        return hasItems;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    //    NOTE Utility Methods ----------------------------------------

    public boolean addItem(Item item) {
        if(item != null) {
            if (!items.containsKey(item.getName())) {
                items.put(item.getName(), item);
                // TODO RoomMessage upon successfully executing addItem
                System.out.printf("Item '%s' was dropped in '%s'.", item.getName(), roomDescription.getName());
                hasItems = true;
                return true;
            } else {
                // TODO RoomMessage upon failing to addItem
                return false;
            }
        } else {
            // TODO ServerMessage upon trying addItem with null
            // NOTE Maybe also need to handle something else, because why is item null?
            return false;
        }
    }

    public Item removeItem(String name) {
        if (items.containsKey(name)) {
            Item item = items.get(name);
            items.remove(item);
            // TODO RoomMessage upon successfully executing removeItem
            System.out.printf("%s was removed from %s.", name, roomDescription.getName());
            if(items.isEmpty()) {
                hasItems = false;
            }
            return item;
        } else {
            // TODO RoomMessage upon failing to removeItem (item is not in room)
            System.err.printf("%s is not in %s.", name, roomDescription.getName());
            return null;
        }
    }

    public boolean addPlayer(Player player) {
        if(player != null && !players.containsKey(player.getPlayerName())) {
            players.put(player.getPlayerName(), player);
            // TODO RoomMessage upon player entering room (Prints arrival of player to other players)
            // TODO PlayerMessage upon player entering room (Prints room description to Player)
            System.out.printf("Player '%s' entered '%s'.", player.getPlayerName(), roomDescription.getName());
            return true;
        } else {
            System.err.printf("Player is already inside '%s' or object was 'null'.", roomDescription.getName());
            return false;
        }
    }

    public boolean removePlayer(Player player) {
        if(player != null && players.containsKey(player.getPlayerName())) {
            players.remove(player.getPlayerName());
            // TODO RoomMessage upon player leaving room (Prints departure of player to other players)
            // TODO PlayerMessage upon player leaving room (Prints leave message to Player)
            System.out.printf("Player '%s' has left '%s'.", player.getPlayerName(), roomDescription.getName());
            return true;
        } else {
            System.err.printf("Player is not in '%s' or object was 'null'.", roomDescription.getName());
            return false;
        }
    }

    public boolean addDoor(Door door) {
        // FIXME Adding doors to Room currently doesn't edit Door object (Necessary for inside/outside)
        if(door != null && !doors.containsKey(door.getDoorDescription().getName())) {
            doors.put(door.getDoorDescription().getName(), door);
            System.out.printf("Door '%s' was added to '%s'.",
                    door.getDoorDescription().getName(),
                    roomDescription.getName());
            hasDoors = true;
            return true;
        } else {
            System.err.printf("Door '%s' already exists or object was 'null'.", door.getDoorDescription().getName());
            return false;
        }
    }

    public boolean removeDoor(Door door) {
        if(door != null && doors.containsKey(door.getDoorDescription().getName())) {
            doors.remove(door.getDoorDescription().getName());
            System.out.printf("Door '%s' was removed from '%s'.",
                    door.getDoorDescription().getName(),
                    roomDescription.getName());
            if(doors.isEmpty()) {
                hasDoors = false;
            }
            return true;
        } else {
            System.err.printf("Door '%s' not in room or object was 'null'.", door.getDoorDescription().getName());
            return false;
        }
    }

    // FIXME Currently adding directions doesn't consider adding the opposite direction to the target room
    public boolean addDirection(Direction direction, Room target) {
        if(direction != null && target != null) {
            if(!directions.containsKey(direction)) {
                directions.put(direction, target);
                System.out.printf("Added '%s' leading to '%s' to Room '%s'.",
                        direction.getDirName(),
                        target.getRoomDescription().getName(),
                        roomDescription.getName());
                return true;
            } else {
                System.err.printf("Direction '%s' already exists in '%s'.",
                        direction.getDirName(),
                        roomDescription.getName());
                return false;
            }
        } else {
            System.err.println("Direction or Target object is 'null'.");
            return false;
        }
    }

    // FIXME Currently removing directions doesn't consider removing the opposite direction from target room
    public boolean removeDirection(Direction direction) {
        if(direction != null && directions.containsKey(direction)) {
            directions.remove(direction);
            System.out.printf("Direction '%s' removed from '%s'.",
                    direction.getDirName(),
                    roomDescription.getName());
            return true;
        } else {
            System.err.printf("Direction doesn't exist in '%s' or object is null.",
                    roomDescription.getName());
            return false;
        }
    }

    @Override
    public String toString() {;
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
        items.forEach((k,v) -> {
            strBuilder.append(v.getRoomDesc());
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
}
