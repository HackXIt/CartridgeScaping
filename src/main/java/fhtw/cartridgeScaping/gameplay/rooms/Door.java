package fhtw.cartridgeScaping.gameplay.rooms;

import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.util.Keyable;
import fhtw.cartridgeScaping.gameplay.util.Lockable;

public class Door extends GameObject implements Lockable {
    protected DoorDescription doorDescription;
    protected Room roomDestination;
    protected Room roomSource;
    protected boolean isLocked = false;
    protected boolean isOpen = false;

    // TODO Fix constructors in Door

    public Door(DoorDescription doorDescription, Room roomDestination, Room roomSource) {
        this.doorDescription = doorDescription;
        this.roomDestination = roomDestination;
        this.roomSource = roomSource;
    }

    public Door(DoorDescription doorDescription, Room roomDestination, Room roomSource, boolean locked) {
        this.doorDescription = doorDescription;
        this.roomDestination = roomDestination;
        this.roomSource = roomSource;
        this.isLocked = locked;
    }

    public Room getRoomDestination() {
        return roomDestination;
    }

    public void setRoomDestination(Room roomDestination) {
        this.roomDestination = roomDestination;
    }

    public Room getRoomSource() {
        return roomSource;
    }

    public void setRoomSource(Room roomSource) {
        this.roomSource = roomSource;
    }

    public DoorDescription getDoorDescription() {
        return doorDescription;
    }

    public void setDoorDescription(DoorDescription doorDescription) {
        this.doorDescription = doorDescription;
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

    @Override
    public String lookAt() {
        return doorDescription.toString();
    }

    @Override
    public String inspect() {
        return doorDescription.getDetailedDescription();
    }
}
