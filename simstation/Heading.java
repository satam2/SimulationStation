package simstation;

import mvc.Utilities;

public class Heading {
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    private int direction;

    public static Heading random() {
        return new Heading();
    }

    public static int randomDirection() {
        return Utilities.rng.nextInt(4); // Generate a random number between 0 and 3
    }

    public Heading() {
        direction = randomDirection();
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getHeading() {
        switch (direction) {
            case NORTH:
                return "NORTH";
            case SOUTH:
                return "SOUTH";
            case EAST:
                return "EAST";
            case WEST:
                return "WEST";
            default:
                return "UNKNOWN";
        }
    }
}
