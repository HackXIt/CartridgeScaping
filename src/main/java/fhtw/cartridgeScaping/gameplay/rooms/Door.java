package fhtw.cartridgeScaping.gameplay.rooms;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.util.Keyable;
import fhtw.cartridgeScaping.gameplay.util.Lockable;
import fhtw.cartridgeScaping.json.DoorSerializer;
import fhtw.cartridgeScaping.networking.NetworkManager;

import java.net.Inet4Address;

@JsonSerialize(using = DoorSerializer.class)
public class Door extends GameObject<Door> implements Lockable {
    protected DoorDescription doorDescription;
    protected Room roomSource;
    protected Room roomDestination;
    protected boolean isLocked = false;
    protected boolean isOpen = false;

    // TODO Fix constructors in Door

    public Door(DoorDescription doorDescription) {
        super(false); // NOTE Doors can never be held
        this.doorDescription = doorDescription;
    }

    public Door(DoorDescription doorDescription, boolean locked) {
        this(doorDescription);
        this.isLocked = locked;
    }

    public Door(DoorDescription doorDescription, boolean locked, boolean opened) {
        this(doorDescription);
        this.isLocked = locked;
        this.isOpen = opened;
    }

    public Door(Door door, String originalID) {
        super(door, originalID);
        this.doorDescription = door.getDoorDescription().cloneDescription();
        this.roomSource = door.getRoomSource();
        this.roomDestination = door.getRoomDestination();
    }

    public Room getRoomSource() {
        return roomSource;
    }

    public void setRoomSource(Room roomSource) {
        this.roomSource = roomSource;
    }

    public void setRoomDestination(Room roomDestination) {
        this.roomDestination = roomDestination;
    }

    public Room getRoomDestination() {
        return roomDestination;
    }

    public void setRooms(Room source, Room destination) throws NullPointerException {
        if(source == null || destination == null) {
            throw new NullPointerException("Cannot set source or destination of door to null.");
        }
        source.addDoor(this);
        destination.addDoor(this);
        roomSource = source;
        roomDestination = destination;
    }

    public DoorDescription getDoorDescription() {
        return doorDescription;
    }

    public void setDoorDescription(DoorDescription doorDescription) {
        this.doorDescription = doorDescription;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean attemptUnlock(Keyable keyable) {
        if(isLocked) {
            isLocked = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean open() {
        if(!isOpen) {
            if (isLocked) {
                //            TODO RoomMessage upon failing to open door because of lock.
                return false;
            } else {
                //            TODO RoomMessage upon successfully opening door.
                isOpen = true;
                return true;
            }
        } else {
//            TODO RoomMessage with notification that door is already open.
            return false;
        }
    }

    public boolean close() {
        if(isOpen) {
            isOpen = false;
            // TODO RoomMessage upon successfully closing door.
            return true;
        } else {
            // TODO RoomMessage with notification that door is already closed.
            return false;
        }
    }

    /* NOTE Cloning doors is only allowed for Host because of original reference to objectID */
    @Override
    public Door cloneObject() {
        if(NetworkManager.getInstance().isHost()) {
            return new Door(this, String.valueOf(hashCode()));
        }
        return null;
    }

    @Override
    public String lookAt() {
        return doorDescription.toString();
    }

    @Override
    public String inspect() {
        return doorDescription.getDetailedDescription();
    }

    @Override
    public String getName() {
        return doorDescription.getName();
    }
}
