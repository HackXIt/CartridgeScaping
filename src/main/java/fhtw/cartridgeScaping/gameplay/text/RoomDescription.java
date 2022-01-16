package fhtw.cartridgeScaping.gameplay.text;

public class RoomDescription extends Description{

    public RoomDescription(String name) {
        super(name);
    }

    public RoomDescription(String name, String shortDescription) {
        super(name, shortDescription);
    }

    public RoomDescription(String name, String shortDescription, String longDescription) {
        super(name, shortDescription, longDescription);
    }

    public RoomDescription(RoomDescription roomDescription) {
        super(roomDescription);
    }

    @Override
    public Description cloneDescription() {
        return new RoomDescription(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
