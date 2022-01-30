package fhtw.cartridgeScaping.gameplay.rooms;

import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.util.Keyable;
import fhtw.cartridgeScaping.gameplay.util.Lockable;

// TODO Refactor this class - as mentioned in KeyCode
public final class CodeDoor extends Door implements Lockable {
    private int code;
    private int unlockAttempts = 0;
    private int maxAttempts = 3;

    public CodeDoor(DoorDescription doorDescription, int code) {
        super(doorDescription);
        this.code = code;
        isLocked = true;
    }

    @Override
    public boolean attemptUnlock(Keyable keyable) {
        if(isLocked) {
            if (this.code == (Integer) keyable.getKey()) {
                isLocked = false;
                // TODO RoomMessage upon successfully unlocking door.
                return true;
            } else {
                unlockAttempts++;
                // TODO RoomMessage upon failing to unlock door.
                return false;
            }
        } else {
            // TODO RoomMessage with notification that door isn't locked.
            return false;
        }
    }
}
