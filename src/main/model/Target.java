package model;


/*
 * Represents the Target Object
 * Assuming we have a terminal screen of size (250, 150), the target can spawn
 * anywhere in the region between the points (30, 50) and (200, 120).
 */
public class Target {
    private static final int SIZE = 20;
    private static final int MIN_X_COORD = 30;
    private static final int MIN_Y_COORD = 50;
    private static final int MAX_X_COORD = 200;
    private static final int MAX_Y_COORD = 120;

    private int coordX;
    private int coordY;
    
    // EFFECTS: constructs a new target object at random location
    public Target() {
        this.coordX = getRandomX();
        this.coordY = getRandomY();
    }

    // EFFECTS: generates a random x coordinate in the specified range for target
    public int getRandomX() {
        return 0; // stub
    }

    // EFFECTS: generates a random y coordinate in the specified range for target
    public int getRandomY() {
        return 0; // stub
    }



    // Getter Methods

    // EFFECTS: returns the target's x coordinate
    public int getX() {
        return 0; // stub
    }

    // EFFECTS: returns the target's y coordinate
    public int getY() {
        return 0; // stub
    }

}
