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
        return new Vector2d(ox.first(), oy.first());
    }

    public Vector2d getUpperRight() {
        return new Vector2d(ox.last(), oy.last());
    }

    private void add(Vector2d pos) {
        ox.add(pos.x);
        oy.add(pos.y);
    }

    private void remove(Vector2d pos) {
        ox.remove(pos.x);
        oy.remove(pos.y);
    }

    private SortedSet<Integer> ox = new TreeSet<>();
    private SortedSet<Integer> oy = new TreeSet<>();
}
