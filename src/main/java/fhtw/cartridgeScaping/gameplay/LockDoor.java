package fhtw.cartridgeScaping.gameplay;

public class LockDoor extends Door implements Lockable {
    private Keyable keyable;

    public LockDoor(DoorDescription doorDescription, Room roomDestination, Room roomSource, Keyable keyable) {
        super(doorDescription, roomDestination, roomSource);
        this.keyable = keyable;
        isLocked = true;
    }

    @Override
    public boolean attemptUnlock(Keyable keyable) {
        if(isLocked) {
            if (this.keyable.equals(keyable.getKey())) {
                isLocked = false;
                // TODO RoomMessage upon successfully unlocking door.
                return true;
            } else {
                // TODO RoomMessage upon failing to unlock door.
                return false;
            }
        } else {
            // TODO RoomMessage with notification that door isn't locked.
            return false;
        }
    }
}
