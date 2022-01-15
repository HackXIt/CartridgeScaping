package fhtw.cartridgeScaping.gameplay;

import java.util.concurrent.locks.Lock;

public class Key extends Item implements Keyable {
    private Lockable lock;

    public Key(ItemDescription itemDesc, Lockable lock) {
        super(itemDesc);
        this.lock = lock;
    }

    public Key(Key key) {
        super(key);
        this.lock = (Lockable) key.getKey();
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
