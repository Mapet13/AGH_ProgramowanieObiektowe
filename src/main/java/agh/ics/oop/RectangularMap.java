package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(getLowerLeft())
                && position.precedes(getUpperRight())
                && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();

        if (!canMoveTo(position))
            return false;

        animals.add(animal);

        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals) {
            if (animal.getPosition().equals(position))
                return animal;
        }

        return null;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }

    private Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    private Vector2d getUpperRight() {
        return new Vector2d(width, height);
    }

    private final ArrayList<Animal> animals = new ArrayList<>();
    private final int width;
    private final int height;
}
