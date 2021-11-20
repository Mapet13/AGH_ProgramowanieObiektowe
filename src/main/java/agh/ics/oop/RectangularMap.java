package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position)
                && position.follows(getLowerLeft())
                && position.precedes(getUpperRight());
    }

    @Override
    public Object objectAt(Vector2d position) {
        return specificObjectAt(animals, position);
    }

    protected Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    protected Vector2d getUpperRight() {
        return new Vector2d(width, height);
    }

    private final int width;
    private final int height;
}
