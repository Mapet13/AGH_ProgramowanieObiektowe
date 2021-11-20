package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GrassFieldTests {

    @Test
    void testPLaceAnimal() {
        GrassField map = new GrassField(0);

        assertTrue(map.place(new Animal(map, new Vector2d(1, 1))));
        assertFalse(map.place(new Animal(map, new Vector2d(1, 1))));
    }

    @Test
    void testMoveAnimalOnOtherAnimalPlace() {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(1, 1);

        map.place(new Animal(map, pos));

        assertFalse(map.canMoveTo(pos));
    }

    @Test
    void testIsOccupied() {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(1, 1);

        map.place(new Animal(map, pos));

        assertTrue(map.isOccupied(pos));
    }


    @Test
    void testAnimalObjectAt() {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(1, 1);
        Animal animal = new Animal(map, pos);

        map.place(animal);

        assertEquals(animal, map.objectAt(pos));
    }

    @Test
    void testGrassCount() {
        final int grassCount = 4;
        final int maxGrassCoordinate = (int)Math.round(Math.sqrt(grassCount * 10));

        GrassField map = new GrassField(grassCount);

        int encountered = 0;
        for (int i = 0; i <= maxGrassCoordinate; ++i) {
            for (int j = 0; j <= maxGrassCoordinate; ++j) {
                if (map.isOccupied(new Vector2d(i, j))) {
                    encountered++;
                }
            }
        }

        assertEquals(grassCount, encountered);
    }


}














