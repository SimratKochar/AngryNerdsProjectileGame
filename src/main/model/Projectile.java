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
    private float coordX;
    private float coordY;

    // REQUIRES: velocity > 0, 0 degrees < angle < 90 degrees
    // EFFECTS: constructs a projectile with given velocity and angle at position(0, 250)
    public Projectile(int velocity,  int angle) {
        this.velocity = velocity;
        this.angle = angle;
        this.coordX = INITIAL_X;
        this.coordY = INITIAL_Y;
    }

    // REQUIRES: time > 0
    // MODIFIES: this
    // EFFECTS: update the projectile position at a specified time using kinematics equations
    public void updateCoordinates(float time) {
        this.coordX = (float) (velocity * time * Math.cos(Math.toRadians(angle)));
        this.coordY = (float) (INITIAL_Y - velocity * time * Math.sin(Math.toRadians(angle)) 
                + 0.5 * 9.81 * time * time);
    }

    // EFFECTS: returns the maximum height reached by projectile
    public double maxHeight() {
        return velocity * velocity * Math.pow(Math.sin(Math.toRadians(angle)), 2) / 19.62;
    }

    // EFFECTS: returns the approximate flight time of the projectile
    public double flightTime() {
        return 2 * velocity * Math.sin(Math.toRadians(angle)) / 9.81;
    }

    // Referenced from Lab 2 - PaddleBall
    // https://github.students.cs.ubc.ca/CPSC210/Lab2-PaddleBall-Starter
    // EFFECTS: returns true if the target is hit by the projectile
    public boolean targetHit(Target target) {
        Rectangle projectileRectangle = new Rectangle(Math.round(getX() - SIZE / 2),
                Math.round(getY() - SIZE / 2), SIZE, SIZE);
        Rectangle targetRectangle = new Rectangle(Math.toIntExact(Math.round(target.getX() - Target.SIZE / 2)),
                Math.toIntExact(Math.round(target.getY() - Target.SIZE / 2)), Target.SIZE, Target.SIZE);
        return  projectileRectangle.intersects(targetRectangle);
    }

    // EFFECTS: returns true if the projectile has hit the ground
    public boolean groundHit() {
        return this.coordY > 250;
    }






    // Getter Methods

    // EFFECTS: returns the velocity of projectile
    public int getVelocity() {
        return this.velocity;
    }

    // EFFECTS: returns the angle of projectile
    public int getAngle() {
        return this.angle;
    }
 
    // EFFECTS: returns the x coordinate of projectile
    public float getX() {
        return this.coordX;
    }

    // EFFECTS: returns the y coordinate of projectile
    public float getY() {
        return this.coordY;
    }

}
