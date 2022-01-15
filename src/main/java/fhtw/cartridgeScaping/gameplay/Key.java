package fhtw.cartridgeScaping.gameplay;

public class KeyLock extends Item implements Key {
    private Lockable lock;

    public KeyLock(ItemDescription itemDesc, Lockable lock) {
        super(itemDesc);
        this.lock = lock;
    }

    public KeyLock(Item item, Lockable lock) {
        super(item);
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
