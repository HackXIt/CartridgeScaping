package fhtw.cartridgeScaping.game;

public class Door {

    private Room roomDestination;
    private Room roomSource;

    public Door(Room roomDestination, Room roomSource) {
        this.roomDestination = roomDestination;
        this.roomSource = roomSource;
    }

    public Room getRoomDestination() {
        return roomDestination;
    }

    public void setRoomDestination(Room roomDestination) {
        this.roomDestination = roomDestination;
    }

    public Room getRoomSource() {
        return roomSource;
    }

    public void setRoomSource(Room roomSource) {
        this.roomSource = roomSource;
    }
}
