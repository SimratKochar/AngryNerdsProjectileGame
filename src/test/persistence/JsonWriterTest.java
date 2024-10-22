package persistence;

import org.junit.jupiter.api.Test;

import model.Projectile;
import model.ProjectileList;
import model.Target;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Target t = new Target();
            ProjectileList pl = new ProjectileList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyProjectileList() {
        try {
            Target t = new Target();
            ProjectileList pl = new ProjectileList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProjectileList.json");
            writer.open();
            writer.write(t, pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProjectileList.json");
            t = reader.readTarget();
            assertTrue(30 <= t.getX() && 200 >= t.getX()); // checks random x coordinate of Target
            assertTrue(50 <= t.getY() && 120 >= t.getY()); // checks random y coordinate of Target
            pl = reader.readPList();
            assertEquals(0, pl.getNumAttempts());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGameProgress() {
        try {
            Target t = new Target();
            ProjectileList pl = new ProjectileList();
            pl.addProjectile(new Projectile(60, 30));
            pl.addProjectile(new Projectile(45, 45));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameProgress.json");
            writer.open();
            writer.write(t, pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGameProgress.json");
            t = reader.readTarget();
            assertTrue(30 <= t.getX() && 200 >= t.getX()); // checks random x coordinate of Target
            assertTrue(50 <= t.getY() && 120 >= t.getY()); // checks random y coordinate of Target
            pl = reader.readPList();
            List<Projectile> projectiles = pl.getProjectiles();
            assertEquals(2, projectiles.size());
            checkProjectile(60, 30, projectiles.get(0));
            checkProjectile(45, 45, projectiles.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
