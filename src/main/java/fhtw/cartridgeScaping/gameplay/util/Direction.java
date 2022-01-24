package fhtw.cartridgeScaping.gameplay.util;

public enum Direction {
    NORTH(0, 1, "north", "You can go north."),
    EAST(1, 0, "east", "You can go east."),
    SOUTH(0, -1, "south", "You can go south."),
    WEST(-1, 0, "west", "You can go west.");

    private final int dx;
    private final int dy;
    private final String dirName;
    private final String dirDesc;

    private static String[] possibleDirections = {"north", "east", "south", "west"};

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

    public static Direction getDirection(String direction) throws IllegalStateException{
        return switch(direction) {
            case "north" -> NORTH;
            case "east" -> EAST;
            case "south" -> SOUTH;
            case "west" -> WEST;
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    public static String[] getPossibleDirections() {
        return possibleDirections;
    }

    public static String getPossibleDirectionsAsSyntax() {
        return "go <" + String.join(",", possibleDirections) + ">";
    }
}
