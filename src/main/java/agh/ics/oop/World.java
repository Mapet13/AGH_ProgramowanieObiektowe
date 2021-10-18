package agh.ics.oop;

import java.util.ArrayList;

public class World {
    public static void main(String[] args) {
        System.out.println("System Start");

        ArrayList<MoveDirection> directions = new ArrayList<>();
        for(String arg : args) {
            switch (arg) {
                case "f":
                    directions.add(MoveDirection.FORWARD);
                    break;
                case "b":
                    directions.add(MoveDirection.BACKWARD);
                    break;
                case "r":
                    directions.add(MoveDirection.RIGHT);
                    break;
                case "l":
                    directions.add(MoveDirection.LEFT);
                    break;
            };
        }

        run(directions);

        System.out.println("System Finish");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));


        System.out.println(MapDirection.NORTH.toUnitVector());
    }

    private static void run(ArrayList<MoveDirection> directions) {
        for(MoveDirection dir : directions) {
            System.out.print(switch (dir) {
                case FORWARD -> "The pet is walking forward\n";
                case BACKWARD -> "The pet is walking back\n";
                case RIGHT -> "The pet is walking right\n";
                case LEFT -> "The pet is walking left\n";
            });
        }
    }
}
