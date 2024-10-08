package model;

import java.util.ArrayList;
import java.util.List;

/*
 * Stores the List of projectiles fired until now
 * This will be used to display the list of parameters set by the user previously
 */
public class ProjectileList {
    private ArrayList<Projectile> projectiles;

    // EFFECTS: constructs an empty list of projectiles
    public ProjectileList() {
        projectiles = new ArrayList<Projectile>();
    }

    // MODIFIES: this
    // EFFECTS: adds a projectile to the list of projectiles
    public void addProjectile(Projectile projectile) {
        // stub
    }

    // EFFECTS: creates a list of velocities of projectiles fired until now
    public List<Integer> listVelocities() {
        return null;   // stub
    }

    // EFFECTS: creates a list of angles of projectiles fired until now
    public List<Integer> listAngles() {
        return null;   // stub
    }




    // Getter Methods

    // EFFECTS: returns the list of projectiles
    public List<Projectile> getProjectiles() {
        return null; // stub
    }

    // EFFECTS: returns the number of projectiles in list to represent attempts
    public int getNumAttempts() {
        return 0; // stub
    }

}
