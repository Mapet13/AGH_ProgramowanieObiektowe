package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.println(map.toString());
    }
}
