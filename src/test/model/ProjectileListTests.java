package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ProjectileListTests {
    private ProjectileList projectileList;
    private Projectile projectile1;
    private Projectile projectile2;
    private Projectile projectile3;

    @BeforeEach
    public void setup() {
        projectileList = new ProjectileList();
        projectile1 = new Projectile(20, 30);
        projectile2 = new Projectile(30, 45);
        projectile3 = new Projectile(50, 60);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, projectileList.getNumAttempts());
    }

    @Test
    public void testAddProjectileOnce() {
        projectileList.addProjectile(projectile1);
        assertEquals(1, projectileList.getNumAttempts());
        assertEquals(projectile1, projectileList.getProjectiles().get(0));
    }

    @Test
    public void testAddProjectileMultiple() {
        projectileList.addProjectile(projectile1);
        assertEquals(1, projectileList.getNumAttempts());
        projectileList.addProjectile(projectile2);
        assertEquals(2, projectileList.getNumAttempts());
        projectileList.addProjectile(projectile3);
        assertEquals(3, projectileList.getNumAttempts());

        assertEquals(projectile1, projectileList.getProjectiles().get(0));
        assertEquals(projectile2, projectileList.getProjectiles().get(1));
        assertEquals(projectile3, projectileList.getProjectiles().get(2));
    }

    @Test
    public void testListVelocitiesOneProjectile() {
        projectileList.addProjectile(projectile1);
        List<Integer> velocities = projectileList.listVelocities();
        assertEquals(1, velocities.size());
        assertEquals(20, velocities.get(0));
    }

    @Test
    public void testListVelocitiesMultipleProjectiles() {
        projectileList.addProjectile(projectile1);
        projectileList.addProjectile(projectile2);
        projectileList.addProjectile(projectile3);
        List<Integer> velocities = projectileList.listVelocities();
        assertEquals(3, velocities.size());
        assertEquals(20, velocities.get(0));
        assertEquals(30, velocities.get(1));
        assertEquals(50, velocities.get(2));
    }

    @Test
    public void testListVelocitiesEmptyList() {
        List<Integer> velocities = projectileList.listVelocities();
        assertEquals(null, velocities);
    }

    @Test
    public void testListAnglesEmptyList() {
        List<Integer> angles = projectileList.listAngles();
        assertEquals(null, angles);
    }

    @Test
    public void testListAnglesOneProjectile() {
        projectileList.addProjectile(projectile1);
        List<Integer> angles = projectileList.listAngles();
        assertEquals(1, angles.size());
        assertEquals(30, angles.get(0));
    }

    @Test
    public void testListAnglesMultipleProjectiles() {
        projectileList.addProjectile(projectile1);
        projectileList.addProjectile(projectile2);
        projectileList.addProjectile(projectile3);
        List<Integer> angles = projectileList.listAngles();
        assertEquals(3, angles.size());
        assertEquals(30, angles.get(0));
        assertEquals(45, angles.get(1));
        assertEquals(60, angles.get(2));
    }

    
}
