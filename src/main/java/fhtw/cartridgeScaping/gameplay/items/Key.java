package fhtw.cartridgeScaping.gameplay.items;

import fhtw.cartridgeScaping.gameplay.text.ItemDescription;
import fhtw.cartridgeScaping.gameplay.util.Keyable;
import fhtw.cartridgeScaping.gameplay.util.Lockable;

public class Key extends Item implements Keyable {
    private Lockable lock;

    public Key(boolean canBeHeld, Lockable lock) {
        super(canBeHeld);
        this.lock = lock;
    }

    public Key(boolean canBeHeld, ItemDescription itemDescription, Lockable lock) {
        super(canBeHeld, itemDescription);
        this.lock = lock;
    }

    @Override
    public boolean unlock(Lockable lock) {
        return lock.attemptUnlock(this);
    }

    @Override
    public Object getKey() {
        return lock;
    }
}
