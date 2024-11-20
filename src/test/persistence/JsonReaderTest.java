package persistence;

import org.junit.jupiter.api.Test;

import model.Projectile;
import model.ProjectileList;
import model.Target;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/NonExistentFile.json");

        try {
            Target t = reader.readTarget();
            ProjectileList pl = reader.readPList();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyProjectileList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProjectileList.json");
        try {
            Target t = reader.readTarget();
            checkTarget(88, 157, t);

            ProjectileList pl = reader.readPList();
            assertEquals(0, pl.getNumAttempts());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testGeneralGameProgress() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameProgress.json");
        try {
            Target t = reader.readTarget();
            checkTarget(45, 80, t);

            ProjectileList pl = reader.readPList();
            List<Projectile> projectiles = pl.getProjectiles();
            assertEquals(2, projectiles.size());
            checkProjectile(50, 30, projectiles.get(0));
            checkProjectile(50, 20, projectiles.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
