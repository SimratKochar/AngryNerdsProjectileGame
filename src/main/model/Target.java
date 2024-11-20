package model;

import java.util.Random;
import java.awt.Color;

import org.json.JSONObject;

import persistence.Writable;

/*
 * Represents the Target Object
 * Assuming we have a terminal screen of size (250, 150), the target can spawn
 * anywhere in the region between the points (30, 50) and (200, 120).
 */
public class Target implements Writable {
    public static final int SIZE = 20;
    public static final Color COLOR = Color.RED;
    
    private static final int MIN_X_COORD = 150;
    private static final int MIN_Y_COORD = 200;
    private static final int MAX_X_COORD = 700;
    private static final int MAX_Y_COORD = 500;

    private int coordX;
    private int coordY;
    
    // EFFECTS: constructs a new target object at random location
    public Target() {
        this.coordX = getRandomX();
        this.coordY = getRandomY();
    }

    // EFFECTS: generates a random x coordinate in the specified range for target
    public int getRandomX() {
        Random rand = new Random();
        return rand.nextInt(MAX_X_COORD - MIN_X_COORD) + MIN_X_COORD;
    }

    // EFFECTS: generates a random y coordinate in the specified range for target
    public int getRandomY() {
        Random rand = new Random();
        return rand.nextInt(MAX_Y_COORD - MIN_Y_COORD) + MIN_Y_COORD;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("targetX", coordX);
        json.put("targetY", coordY);
        return json;
    }

    // Setter Methods for tests

    // MODIFIES: this
    // EFFECTS: sets the target's x coordinate to specified value
    public void setX(int x) {
        this.coordX = x;
    }

    // MODIFIES: this
    // EFFECTS: sets the target's y coordinate to specified value
    public void setY(int y) {
        this.coordY = y;
    }

    // Getter Methods

    // EFFECTS: returns the target's x coordinate
    public int getX() {
        return this.coordX;
    }

    // EFFECTS: returns the target's y coordinate
    public int getY() {
        return this.coordY;
    }


}
