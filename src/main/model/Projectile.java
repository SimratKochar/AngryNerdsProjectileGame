package model;

/*
 * Represents the Projectile Object
 */
public class Projectile {
    private static final int INITIAL_X = 0;
    private static final int INITIAL_Y = 250;
    private static final int SIZE = 15;

    private int velocity;
    private int angle;
    private double coordX;
    private double coordY;

    // REQUIRES: velocity > 0, 0 degrees < angle < 90 degrees
    // EFFECTS: constructs a projectile with given velocity and angle at position(0, 100)
    public Projectile(int velocity,  int angle) {
        this.velocity = velocity;
        this.angle = angle;
        this.coordX = INITIAL_X;
        this.coordY = INITIAL_Y;
    }

    // REQUIRES: time > 0
    // MODIFIES: this
    // EFFECTS: update the projectile position at a specified time using kinematics equations
    public void updateCoordinates(double time) {
        // stub
    }

    // EFFECTS: returns the maximum height reached by projectile
    public double maxHeight() {
        return 0;  // stub
    }

    // EFFECTS: returns the approximate flight time of the projectile
    public double flightTime() {
        return 0; // stub
    }

    // EFFECTS: returns true if the target is hit by the projectile
    public boolean targetHit(Target target) {
        return false;  // stub
    }

    // EFFECTS: returns true if the projectile has hit the ground
    public boolean groundHit() {
        return false; // stub
    }






    // Getter Methods

    // EFFECTS: returns the velocity of projectile
    public int getVelocity() {
        return 0; // stub
    }

    // EFFECTS: returns the angle of projectile
    public int getAngle() {
        return 0; // stub
    }
 
    // EFFECTS: returns the x coordinate of projectile
    public int getX() {
        return 0; // stub
    }

    // EFFECTS: returns the y coordinate of projectile
    public int getY() {
        return 0; // stub
    }

}
