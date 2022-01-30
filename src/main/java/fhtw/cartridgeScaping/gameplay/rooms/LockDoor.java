package fhtw.cartridgeScaping.gameplay.rooms;

import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.util.Keyable;
import fhtw.cartridgeScaping.gameplay.util.Lockable;

public class LockDoor extends Door implements Lockable {
    private Keyable keyable;

    public LockDoor(DoorDescription doorDescription, Keyable keyable) {
        super(doorDescription);
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
