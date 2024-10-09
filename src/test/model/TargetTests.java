package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TargetTests {
    private Target target;

    @BeforeEach
    public void setup() {
        target = new Target();
        target.setX(70);
        target.setY(70);
    }

    @Test
    public void testConstructor() {
        assertEquals(70, target.getX());
        assertEquals(70, target.getY());
    }

    @Test
    public void testGetRandomX() {
        int randomX = target.getRandomX();
        assertTrue(randomX >= 30 &&  randomX <= 200);
    }

    @Test
    public void testGetRandomY() {
        int randomY = target.getRandomY();
        assertTrue(randomY >= 50 &&  randomY <= 120);
    }

}
