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

    public KeyCode(ItemDescription itemDesc, int code) {
        super(itemDesc);
        this.code = code;
    }

    public KeyCode(KeyCode item) {
        super(item);
        this.code = (Integer) item.getKey();
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
