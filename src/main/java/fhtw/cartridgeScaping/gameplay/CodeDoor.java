package fhtw.cartridgeScaping.gameplay;

public final class CodeDoor extends Door implements Lockable {
    private int code;
    private int unlockAttempts = 0;
    private int maxAttempts = 3;

    public CodeDoor(DoorDescription doorDescription, Room roomDestination, Room roomSource, int code) {
        super(doorDescription, roomDestination, roomSource);
        this.code = code;
        isLocked = true;
    }

    @Override
    public boolean attemptUnlock(Keyable code) {
        if(isLocked) {
            if (this.code != (Integer) code.getKey()) {
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
