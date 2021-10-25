package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public ArrayList<MoveDirection> parse(String[] input) {
        ArrayList<MoveDirection> directions = new ArrayList<>();
        for(String arg : input) {
            switch (arg) {
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "l" -> directions.add(MoveDirection.LEFT);
            }
        }
        return directions;
    }
}
