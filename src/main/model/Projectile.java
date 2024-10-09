package model;

import java.awt.Rectangle;

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
        this.coordX = velocity * time * Math.cos(Math.toRadians(angle));
        this.coordY = INITIAL_Y - velocity * time * Math.sin(Math.toRadians(angle)) + 0.5 * 9.81 * time * time;
    }

    // EFFECTS: returns the maximum height reached by projectile
    public double maxHeight() {
        return velocity * velocity * Math.pow(Math.sin(Math.toRadians(angle)), 2) / 19.62;
    }

    // EFFECTS: returns the approximate flight time of the projectile
    public double flightTime() {
        return 2 * velocity * Math.sin(Math.toRadians(angle)) / 9.81;
    }

    // EFFECTS: returns true if the target is hit by the projectile
    public boolean targetHit(Target target) {
        Rectangle projectileRectangle = new Rectangle(Math.toIntExact(Math.round(getX() - SIZE / 2)),
                Math.toIntExact(Math.round(getY() - SIZE / 2)), SIZE, SIZE);
        Rectangle targetRectangle = new Rectangle(Math.toIntExact(Math.round(target.getX() - SIZE / 2)),
                Math.toIntExact(Math.round(target.getY() - SIZE / 2)), SIZE, SIZE);
        return  projectileRectangle.intersects(targetRectangle);
    }

    // EFFECTS: returns true if the projectile has hit the ground
    public boolean groundHit() {
        return this.coordY >= 250;
    }






    // Getter Methods

    // EFFECTS: returns the velocity of projectile
    public int getVelocity() {
        return this.velocity; // stub
    }

    // EFFECTS: returns the angle of projectile
    public int getAngle() {
        return this.angle; // stub
    }
 
    // EFFECTS: returns the x coordinate of projectile
    public double getX() {
        return this.coordX; // stub
    }

    // EFFECTS: returns the y coordinate of projectile
    public double getY() {
        return this.coordY; // stub
    }

}
