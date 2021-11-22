package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    public GrassField(int grassCount) {
        final int minCoordinate = 0;
        final int maxCoordinate = (int) Math.round(Math.sqrt(grassCount * 10));

        final Random rand = new Random();
        for (int i = 0; i < grassCount; i++) {
            Vector2d pos;
            do {
                pos = new Vector2d(getRandomInRange(rand, maxCoordinate, minCoordinate), getRandomInRange(rand, maxCoordinate, minCoordinate));
            } while (isOccupied(pos));
            grasses.put(pos, new Grass(pos));
        }
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
        if (animals.isEmpty() && grasses.isEmpty()) {
            return new Vector2d(0, 0); // emergency value
        } else {
            Vector2d lowerLeft;

            if (!animals.isEmpty())
                lowerLeft = animals.values().stream().findAny().get().getPosition();
            else
                lowerLeft = grasses.values().stream().findAny().get().getPosition();

            for(Animal animal : animals.values())
                lowerLeft = lowerLeft.lowerLeft(animal.getPosition());

            for(Grass grass : grasses.values())
                lowerLeft = lowerLeft.lowerLeft(grass.getPosition());

            return lowerLeft;
        }
    }

    @Override
    protected Vector2d getUpperRight() {
        if (animals.isEmpty() && grasses.isEmpty()) {
            return new Vector2d(5, 5); // emergency value
        } else {
            Vector2d upperRight;

            if (!animals.isEmpty())
                upperRight = animals.values().stream().findAny().get().getPosition();
            else
                upperRight = grasses.values().stream().findAny().get().getPosition();

            for(Animal animal : animals.values())
                upperRight = upperRight.upperRight(animal.getPosition());

            for(Grass grass : grasses.values())
                upperRight = upperRight.upperRight(grass.getPosition());

            return upperRight;
        }
    }

    private int getRandomInRange(Random rand, int max, int min) {
        return rand.nextInt(max - min) + min;
    }

    private final Map<Vector2d, Grass> grasses = new LinkedHashMap<>();

}



