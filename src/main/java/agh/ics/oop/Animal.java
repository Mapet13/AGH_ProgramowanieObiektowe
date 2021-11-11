package agh.ics.oop;

public class Animal {

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case FORWARD -> {
                moveIfPossible(position.add(orientation.toUnitVector()));
            }
            case BACKWARD -> {
                moveIfPossible(position.subtract(orientation.toUnitVector()));
            }
            case RIGHT -> {
                orientation = orientation.next();
            }
            case LEFT -> {
                orientation = orientation.previous();
            }
        }
    }

    private void moveIfPossible(Vector2d destination) {
        if(map.canMoveTo(destination))
            position = destination;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case WEST -> "W";
            case SOUTH -> "S";
        };
    }

    private final IWorldMap map;
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
}
