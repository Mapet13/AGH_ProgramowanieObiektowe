package agh.ics.oop;

import java.util.ArrayList;
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
            grasses.add(new Grass(pos));
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
                lowerLeft = animals.get(0).getPosition();
            else
                lowerLeft = grasses.get(0).getPosition();

            for(Animal animal : animals)
                lowerLeft = lowerLeft.lowerLeft(animal.getPosition());

            for(Grass grass : grasses)
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
                upperRight = animals.get(0).getPosition();
            else
                upperRight = grasses.get(0).getPosition();

            for(Animal animal : animals)
                upperRight = upperRight.upperRight(animal.getPosition());

            for(Grass grass : grasses)
                upperRight = upperRight.upperRight(grass.getPosition());

            return upperRight;
        }
    }

    private int getRandomInRange(Random rand, int max, int min) {
        return rand.nextInt(max - min) + min;
    }

    private final ArrayList<Grass> grasses = new ArrayList<>();

}



