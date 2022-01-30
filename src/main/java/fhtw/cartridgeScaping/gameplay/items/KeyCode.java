package fhtw.cartridgeScaping.gameplay.items;

import fhtw.cartridgeScaping.gameplay.text.ItemDescription;
import fhtw.cartridgeScaping.gameplay.util.Keyable;
import fhtw.cartridgeScaping.gameplay.util.Lockable;

/**
 * NOTE TODOs for this class
 * TODO Refactor KeyCode and CodeDoor - A code is not a physical thing.
 * NOTE It could be however, if this Item is a representation of the code in form of a hint
 * NOTE The players would "use" the hint to attemptUnlock on the Door.
 */
public class KeyCode extends Item implements Keyable {
    private int code;

    public KeyCode(boolean canBeHeld, int code) {
        super(canBeHeld);
        this.code = code;
    }

    public KeyCode(boolean canBeHeld, ItemDescription itemDescription, int code) {
        super(canBeHeld, itemDescription);
        this.code = code;
    }

    @Override
    public boolean unlock(Lockable lock) {
        return lock.attemptUnlock(this);
    }

    @Override
    public Object getKey() {
        return code;
    }
}
