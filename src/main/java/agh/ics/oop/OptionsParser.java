package agh.ics.oop;

import java.util.List;
import java.util.ArrayList;

public class OptionsParser {
    public ArrayList<MoveDirection> parse(List<String> input) throws IllegalArgumentException {
        ArrayList<MoveDirection> directions = new ArrayList<>();
        for(String arg : input) {
            switch (arg) {
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "l" -> directions.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }
        return directions;
    }
}
