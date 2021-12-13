package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegrationTests {

    @Test
    void testFinalPosition() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f" ,"f", "f", "f"};
        ArrayList<MoveDirection> directions = new OptionsParser().parse(Arrays.stream(args).toList());
        IWorldMap map = new RectangularMap(10, 5);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(3, 5)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
    }

    @Test
    void testNoMoves() {
        String[] args = {};
        ArrayList<MoveDirection> directions = new OptionsParser().parse(Arrays.stream(args).toList());
        IWorldMap map = new RectangularMap(10, 5);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    void testNoAnimals() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f" ,"f", "f", "f"};
        ArrayList<MoveDirection> directions = new OptionsParser().parse(Arrays.stream(args).toList());
        IWorldMap map = new RectangularMap(10, 5);
        ArrayList<Vector2d> positions = new ArrayList<>();
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                assertFalse(map.isOccupied(new Vector2d(x, y)));
            }
        }
    }

    @Test
    void testAnimalsCollisions() {
        String[] args = {"f", "r", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f" ,"f", "f", "f"};
        ArrayList<MoveDirection> directions = new OptionsParser().parse(Arrays.stream(args).toList());
        IWorldMap map = new RectangularMap(10, 5);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(2, 3)));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void testPlaceOutsideBorder() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f" ,"f", "f", "f"};
        ArrayList<MoveDirection> directions = new OptionsParser().parse(Arrays.stream(args).toList());
        IWorldMap map = new RectangularMap(10, 5);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(200, 2000), new Vector2d(-2, -3)));

        assertThrows(IllegalArgumentException.class, () -> new SimulationEngine(directions, map, positions));
    }

    @Test
    void testBorderCollisions() {
        String[] args = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f" ,"f", "f", "f"};
        ArrayList<MoveDirection> directions = new OptionsParser().parse(Arrays.stream(args).toList());
        IWorldMap map = new RectangularMap(10, 5);
        ArrayList<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(0, 0)));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(0, 5)));
    }

}
