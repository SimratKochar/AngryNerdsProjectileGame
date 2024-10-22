package persistence;

import java.io.*;

import org.json.JSONObject;

import model.ProjectileList;
import model.Target;

// Referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads Target from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Target readTarget() throws IOException {
        return null; //stub
    }
    
    // EFFECTS: reads ProjectileList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProjectileList readPList() throws IOException {
        return null; // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null; // stub
    }

    // EFFECTS: parses Target from JSON object and returns it
    public Target makeTarget(JSONObject jsonObject) {
        return null; // stub
    }

    // EFFECTS: parses projectileList from JSON object and returns it
    public ProjectileList makeProjectileList(JSONObject jsonObject) {
        return null; //stub
    }

    // MODIFIES: projectileList
    // EFFECTS: parses projectiles from JSON object and adds them to projectileList
    private void addProjectiles(ProjectileList projectileList, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: projectileList
    // EFFECTS: parses projectile from JSON object and adds it to projectileList
    private void addProjectile(ProjectileList projectileList, JSONObject jsonObject) {
        // stub
    }

}
