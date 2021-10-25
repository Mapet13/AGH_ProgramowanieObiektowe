package agh.ics.oop;

public class Animal {

    public void move(MoveDirection direction) {
        switch(direction) {
            case FORWARD -> {
                moveForward(position.add(orientation.toUnitVector()));
            }
            case BACKWARD -> {
                moveForward(position.subtract(orientation.toUnitVector()));
            }
            case RIGHT -> {
                orientation = orientation.next();
            }
            case LEFT -> {
                orientation = orientation.previous();
            }
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Position: " + position.toString() + "; Direction: " + orientation.toString();
    }

    private void moveForward(Vector2d nextPos) {
        if (nextPos.follows(mapMin) && nextPos.precedes(mapMax)) {
            position = nextPos;
        }
    }

    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;

    private final Vector2d mapMax = new Vector2d(4, 4);
    private final Vector2d mapMin = new Vector2d(0, 0);
}
