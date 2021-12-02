package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        remove(oldPosition);
        add(newPosition);
    }

    public void add(IMapElement obj) {
        add(obj.getPosition());
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(ox.first().x, oy.first().y);
    }

    public Vector2d getUpperRight() {
        return new Vector2d(ox.last().x, oy.last().y);
    }

    private void add(Vector2d pos) {
        ox.add(pos);
        oy.add(pos);
    }

    private void remove(Vector2d pos) {
        ox.remove(pos);
        oy.remove(pos);
    }

    private SortedSet<Vector2d> ox = new TreeSet<>(Comparator.comparingInt(o -> o.x));
    private SortedSet<Vector2d> oy = new TreeSet<>(Comparator.comparingInt(o -> o.y));
}
