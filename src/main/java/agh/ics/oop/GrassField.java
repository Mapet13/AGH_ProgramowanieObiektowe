package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    public GrassField(int grassCount) {
        this(new MapBoundary(), grassCount);
    }

    public GrassField(MapBoundary mapBoundary, int grassCount) {
        this.mapBoundary = mapBoundary;

        maxGrassCoordinate = (int) Math.round(Math.sqrt(grassCount * 10));

        final Random rand = new Random();
        for (int i = 0; i < grassCount; i++) {
            putNewGrass(rand);
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        super.positionChanged(oldPosition, newPosition);

        if (grasses.containsKey(newPosition)) {
            grasses.remove(newPosition);
            putNewGrass(new Random());
        }
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException {
        super.place(animal);
        mapBoundary.add(animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object animal = specificObjectAt(animals, position);
        if (animal != null)
            return animal;
        return specificObjectAt(grasses, position);
    }

    @Override
    public Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    private int getRandomInCorrectRange(Random rand) {
        return rand.nextInt(maxGrassCoordinate - minGrassCoordinate) + minGrassCoordinate;
    }

    private void putNewGrass(Random rand) {
        Vector2d pos;
        do {
            pos = new Vector2d(getRandomInCorrectRange(rand), getRandomInCorrectRange(rand));
        } while (isOccupied(pos));
        Grass grass = new Grass(pos);
        grasses.put(pos, grass);
        mapBoundary.add(grass);
    }

    private final Map<Vector2d, Grass> grasses = new LinkedHashMap<>();
    private final MapBoundary mapBoundary;
    private final int minGrassCoordinate = 0;
    private final int maxGrassCoordinate;

}



