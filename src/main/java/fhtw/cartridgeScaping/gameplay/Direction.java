package fhtw.cartridgeScaping.game;

public enum Direction {
    NORTH(),
    EAST(),
    SOUTH(),
    WEST(),
    IN(),
    OUT();

    private int dx;
    private int dy;
    private Door door;

    public Direction() {}

    public Direction(Door door) {
        this.door = door;
    }

    public Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
