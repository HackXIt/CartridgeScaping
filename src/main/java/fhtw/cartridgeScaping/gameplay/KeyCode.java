package fhtw.cartridgeScaping.gameplay;

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
