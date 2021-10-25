package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AnimalMoveTest {

    @Test
    void testForward() {
        assertEquals(new Vector2d(2, 3), getAnimalAfterMove(new String[]{"f"}).getPosition());
    }

    @Test
    void testBackward() {
        assertEquals(new Vector2d(2, 1), getAnimalAfterMove(new String[]{"b"}).getPosition());
    }

    @Test
    void testRight() {
        assertEquals(MapDirection.EAST, getAnimalAfterMove(new String[]{"r"}).getOrientation());
    }

    @Test
    void testLeft() {
        assertEquals(MapDirection.WEST, getAnimalAfterMove(new String[]{"l"}).getOrientation());
    }

    @Test
    void testRotationNotChangePosition() {
        assertEquals(new Vector2d(2, 2), getAnimalAfterMove(new String[]{"l"}).getPosition());
        assertEquals(new Vector2d(2, 2), getAnimalAfterMove(new String[]{"r"}).getPosition());
    }

    @Test
    void testChangingPositionNotChangeRotation() {
        assertEquals(MapDirection.NORTH, getAnimalAfterMove(new String[]{"f"}).getOrientation());
        assertEquals(MapDirection.NORTH, getAnimalAfterMove(new String[]{"b"}).getOrientation());
    }

    @Test
    void testAdvanceMovement() {
        assertEquals(new Vector2d(4, 2), getAnimalAfterMove(new String[]{"r","f","f"}).getPosition());
        assertEquals(new Vector2d(2, 2), getAnimalAfterMove(new String[]{"b","f","b","b","f","f"}).getPosition());
        assertEquals(new Vector2d(0, 0), getAnimalAfterMove(new String[]{"b","b","l","f","f"}).getPosition());
        assertEquals(new Vector2d(4, 4), getAnimalAfterMove(new String[]{"f","f","r","f","f"}).getPosition());
    }

    @Test
    void testMovementWithMapBorder() {
        assertEquals(new Vector2d(2, 4), getAnimalAfterMove(new String[]{"f","f","f","f","f","f","f","f"}).getPosition());
        assertEquals(new Vector2d(2, 0), getAnimalAfterMove(new String[]{"b","b","b","b","b","b","b","b"}).getPosition());
        assertEquals(new Vector2d(0, 2), getAnimalAfterMove(new String[]{"r", "b","b","b","b","b","b","b","b"}).getPosition());
        assertEquals(new Vector2d(4, 2), getAnimalAfterMove(new String[]{"l", "b","b","b","b","b","b","b","b"}).getPosition());
    }

    private Animal getAnimalAfterMove(String[] args) {
        Animal pet = new Animal();
        OptionsParser parser = new OptionsParser();

        for (MoveDirection direction : parser.parse(args)) {
            pet.move(direction);
        }

        return pet;
    }
}