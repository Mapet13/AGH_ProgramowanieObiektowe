package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RectangularMapTests {

    @Test
    void testCantMoveOutsideTheMap() {
        RectangularMap map = new RectangularMap(10, 10);

        assertFalse(map.canMoveTo(new Vector2d(-10, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, -10)));
        assertFalse(map.canMoveTo(new Vector2d(0, 20)));
        assertFalse(map.canMoveTo(new Vector2d(20, 0)));
    }

    @Test
    void testCanMoveEverywhereOnWholeEmptyMap() {
        final int x = 4;
        final int y = 5;

        RectangularMap map = new RectangularMap(x, y);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                assertTrue(map.canMoveTo(new Vector2d(x, y)));
            }
        }
    }

    @Test
    void testPLaceAnimal() {
        RectangularMap map = new RectangularMap(10, 10);

        assertDoesNotThrow(() -> map.place(new Animal(map, new Vector2d(1, 1))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(1, 1))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(-1, -1))));
    }

    @Test
    void testMoveAnimalOnOtherAnimalPlace() {
        RectangularMap map = new RectangularMap(10, 10);
        Vector2d pos = new Vector2d(1, 1);

        map.place(new Animal(map, pos));

        assertFalse(map.canMoveTo(pos));
    }

    @Test
    void testIsOccupied() {
        RectangularMap map = new RectangularMap(10, 10);
        Vector2d pos = new Vector2d(1, 1);

        map.place(new Animal(map, pos));

        assertTrue(map.isOccupied(pos));
    }

    @Test
    void testEmptyMapIsNotOccupied() {
        final int x = 4;
        final int y = 5;

        RectangularMap map = new RectangularMap(x, y);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                assertFalse(map.isOccupied(new Vector2d(x, y)));
            }
        }
    }

    @Test
    void testObjectAt() {
        RectangularMap map = new RectangularMap(10, 10);
        Vector2d pos = new Vector2d(1, 1);
        Animal animal = new Animal(map, pos);

        map.place(animal);

        assertEquals(animal, map.objectAt(pos));
    }
}
