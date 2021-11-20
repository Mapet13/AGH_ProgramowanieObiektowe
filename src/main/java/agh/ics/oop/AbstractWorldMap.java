package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {

    @Override
    public boolean canMoveTo(Vector2d position) {
        return specificObjectAt(animals, position) == null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
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
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }

    protected Object specificObjectAt(ArrayList<? extends IMapElement> objects, Vector2d position) {
        return objects.stream().filter(obj -> obj.getPosition().equals(position)).findAny().orElse(null);
    }

    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    protected ArrayList<Animal> animals = new ArrayList<>();
}
