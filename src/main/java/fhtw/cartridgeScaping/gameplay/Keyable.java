package fhtw.cartridgeScaping.gameplay;

public interface Keyable {
    boolean unlock(Lockable lock);
    Object getKey();
}
