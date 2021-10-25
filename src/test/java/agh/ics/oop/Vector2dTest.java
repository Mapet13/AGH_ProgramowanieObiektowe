package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        Vector2d vec = new Vector2d(1, -2);
        assertEquals("(1,-2)", vec.toString());
    }

    @Test
    void testPrecedes() {
        Vector2d a = new Vector2d(123, 10);
        Vector2d b = new Vector2d(-100, -200);
        Vector2d c = new Vector2d(-1000, 2000);

        assertTrue(b.precedes(a));
        assertFalse(a.precedes(b));
        assertTrue(a.precedes(a));
        assertFalse(a.precedes(c));
        assertFalse(b.precedes(c));
    }

    @Test
    void testFollows() {
        Vector2d a = new Vector2d(123, 10);
        Vector2d b = new Vector2d(-100, -200);
        Vector2d c = new Vector2d(-1000, 2000);

        assertTrue(a.follows(b));
        assertFalse(b.follows(a));
        assertTrue(a.follows(a));
        assertFalse(a.follows(c));
        assertFalse(b.follows(c));
    }

    @Test
    void testUpperRight() {
        Vector2d a = new Vector2d(123, 10);
        Vector2d b = new Vector2d(-1000, 2000);

        Vector2d expected = new Vector2d(123, 2000);

        assertEquals(expected, a.upperRight(b));
        assertEquals(expected, b.upperRight(a));
    }

    @Test
    void testLowerLeft() {
        Vector2d a = new Vector2d(123, 10);
        Vector2d b = new Vector2d(-1000, 2000);

        Vector2d expected = new Vector2d(-1000, 10);

        assertEquals(expected, a.lowerLeft(b));
        assertEquals(expected, b.lowerLeft(a));
    }

    @Test
    void testAdd() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(3, 4);

        Vector2d expected = new Vector2d(4, 2);

        assertEquals(expected, a.add(b));
        assertEquals(expected, b.add(a));
    }

    @Test
    void testSubtract() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(3, 4);

        Vector2d expected = new Vector2d(-2, -6);

        assertEquals(expected, a.subtract(b));
    }

    @Test
    void testEquals() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(1, -2);
        Vector2d c = new Vector2d(10, -286);

        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(a, a);
        assertNotEquals(a, c);
        assertNotEquals(c, a);
    }

    @Test
    void testOpposite() {
        Vector2d a = new Vector2d(1, -2);
        Vector2d b = new Vector2d(-2, 1);

        assertEquals(b, a.opposite());
    }
}