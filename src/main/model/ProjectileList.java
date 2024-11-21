package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/*
 * Stores the List of projectiles fired until now
 * This will be used to display the list of parameters set by the user previously
 */
public class ProjectileList implements Writable {
    private ArrayList<Projectile> projectiles;

    // EFFECTS: constructs an empty list of projectiles
    public ProjectileList() {
        projectiles = new ArrayList<Projectile>();
    }

    // MODIFIES: this
    // EFFECTS: adds a projectile to the list of projectiles
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    // EFFECTS: creates a list of velocities of projectiles fired until now
    public List<Integer> listVelocities() {
        ArrayList<Integer> velocities = new ArrayList<Integer>();
        
        for (Projectile projectile: projectiles) {
            velocities.add(projectile.getVelocity());
        }
        
        return velocities;
    }

    // EFFECTS: creates a list of angles of projectiles fired until now
    public List<Integer> listAngles() {
        ArrayList<Integer> angles = new ArrayList<Integer>();
        
        for (Projectile projectile: projectiles) {
            angles.add(projectile.getAngle());
        }    

        return angles;
    }

    // Referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("projectiles", projectilesToJson());
        return json;
    }

    // EFFECTS: returns projectiles as a JSON Array
    private JSONArray projectilesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Projectile p: projectiles) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }


    // Getter Methods

    // EFFECTS: returns the list of projectiles
    public List<Projectile> getProjectiles() {
        return projectiles; 
    }

    // EFFECTS: returns the number of projectiles in list to represent attempts
    public int getNumAttempts() {
        return projectiles.size(); 
    }

}
