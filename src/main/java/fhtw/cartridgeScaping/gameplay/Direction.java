package fhtw.cartridgeScaping.gameplay;

public enum Direction {
    NORTH(0, 1, "north", "You can go north."),
    EAST(1, 0, "east", "You can go east."),
    SOUTH(0, -1, "south", "You can go south."),
    WEST(-1, 0, "west", "You can go west.");

    private int dx;
    private int dy;
    private String dirName;
    private String dirDesc;

    Direction(int dx, int dy, String dirName, String dirDesc) {
        this.dx = dx;
        this.dy = dy;
        this.dirName = dirName;
        this.dirDesc = dirDesc;
    }

    public String getDirName() {
        return dirName;
    }

    public String getDirDesc() {
        return dirDesc;
    }
}
