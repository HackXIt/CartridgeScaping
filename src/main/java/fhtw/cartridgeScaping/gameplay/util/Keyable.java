package fhtw.cartridgeScaping.gameplay.util;

public interface Keyable {
    boolean unlock(Lockable lock);
    Object getKey();
}
