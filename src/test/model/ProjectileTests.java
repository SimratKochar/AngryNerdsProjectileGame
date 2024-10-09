package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/*
 * Tests for the Projectile class
 */
public class ProjectileTests {
    private Projectile projectile;
    private Target target1;
    private Target target2;

    @BeforeEach
    public void setup() {
        projectile = new Projectile(30, 45);
    }

    @Test
    public void testConstructor() {
        assertEquals(30, projectile.getVelocity());
        assertEquals(45, projectile.getAngle());
        assertEquals(0, projectile.getX());
        assertEquals(250, projectile.getY());
    }

    @Test
    public void testUpdateCoordinates() {
        projectile.updateCoordinates(2);
        assertEquals(60 / Math.sqrt(2), projectile.getX());
        assertEquals(250 - 60 / Math.sqrt(2) + 19.62, projectile.getY());
    }

    @Test
    public void testMaxHeight() {
        assertEquals(225 / 9.81, projectile.maxHeight());
    }

    @Test
    public void testFlightTime() {
        assertEquals(30 * Math.sqrt(2) / 9.81, projectile.flightTime());
    }

    @Test
    public void testTargetHit() {
        target1 = new Target();
        target1.setX(42);
        target1.setY(227);
        
        target2 = new Target();
        target2.setX(42);
        target2.setY(100);

        projectile.updateCoordinates(2);
        assertTrue(projectile.targetHit(target1));
        assertFalse(projectile.targetHit(target2));
    }

    public void testGroundHit() {
        projectile.updateCoordinates(2);
        assertFalse(projectile.groundHit());
        projectile.updateCoordinates(4.4);
        assertTrue(projectile.groundHit());
    }

}