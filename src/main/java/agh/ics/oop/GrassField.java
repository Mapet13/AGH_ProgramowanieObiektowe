package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    public GrassField (int grassCount) {
        this(new MapBoundary(), grassCount);
    }

    public GrassField(MapBoundary mapBoundary, int grassCount) {
        this.mapBoundary = mapBoundary;

        final int minCoordinate = 0;
        final int maxCoordinate = (int) Math.round(Math.sqrt(grassCount * 10));

        final Random rand = new Random();
        for (int i = 0; i < grassCount; i++) {
            Vector2d pos;
            do {
                pos = new Vector2d(getRandomInRange(rand, maxCoordinate, minCoordinate), getRandomInRange(rand, maxCoordinate, minCoordinate));
            } while (isOccupied(pos));
            Grass grass = new Grass(pos);
            grasses.put(pos, grass);
            mapBoundary.add(grass);
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
    protected Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    protected Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    private int getRandomInRange(Random rand, int max, int min) {
        return rand.nextInt(max - min) + min;
    }

    private final Map<Vector2d, Grass> grasses = new LinkedHashMap<>();
    private final MapBoundary mapBoundary;
}



