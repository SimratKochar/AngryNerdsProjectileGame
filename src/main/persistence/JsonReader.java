package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Projectile;
import model.ProjectileList;
import model.Target;

// Referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Target from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Target readTarget() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return makeTarget(jsonObject);
    }
    
    // EFFECTS: reads ProjectileList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProjectileList readPList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return makeProjectileList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Target from JSON object and returns it
    public Target makeTarget(JSONObject jsonObject) {
        Target target = new Target();
        JSONObject json = (JSONObject) jsonObject.get("Target");
        target.setX(json.getInt("targetX"));
        target.setY(json.getInt("targetY"));
        return target;
    }

    // EFFECTS: parses projectileList from JSON object and returns it
    public ProjectileList makeProjectileList(JSONObject jsonObject) {
        ProjectileList projectileList = new ProjectileList();
        addProjectiles(projectileList,  jsonObject);
        return projectileList;
    }

    // MODIFIES: projectileList
    // EFFECTS: parses projectiles from JSON object and adds them to projectileList
    private void addProjectiles(ProjectileList projectileList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("projectiles");
        for (Object json : jsonArray) {
            JSONObject nextProjectile = (JSONObject) json;
            addProjectile(projectileList, nextProjectile);
        }
    }

    // MODIFIES: projectileList
    // EFFECTS: parses projectile from JSON object and adds it to projectileList
    private void addProjectile(ProjectileList projectileList, JSONObject jsonObject) {
        int velocity = jsonObject.getInt("velocity");
        int angle = jsonObject.getInt("angle");
        Projectile projectile = new Projectile(velocity,angle);
        projectileList.addProjectile(projectile);
    }

}
