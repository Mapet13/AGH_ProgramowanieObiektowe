package agh.ics.oop;

public class Grass implements IMapElement {
    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getImagePath() {
        return "src/main/resources/grass.png";
    }

    @Override
    public String getName() {
        return "Trawa";
    }

    @Override
    public String toString() {
        return "*";
    }

    private final Vector2d position;
}
