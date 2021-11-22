package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition, animals.remove(oldPosition));
    }

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

        animal.addObserver(this);
        animals.put(position, animal);

        return true;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }

    protected Object specificObjectAt(Map<Vector2d, ? extends IMapElement> objects, Vector2d position) {
        return objects.get(position);
    }

    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    protected Map<Vector2d, Animal> animals = new LinkedHashMap<>();
}
