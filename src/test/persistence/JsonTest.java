package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Projectile;
import model.Target;

// Referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkProjectile(int velocity, int angle, Projectile projectile) {
        assertEquals(velocity, projectile.getVelocity());
        assertEquals(angle, projectile.getAngle());
    }

    protected void checkTarget(int targetX, int targetY, Target target) {
        assertEquals(targetX, target.getX());
        assertEquals(targetY, target.getY());
    }
}
