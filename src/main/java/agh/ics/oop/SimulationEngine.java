package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {
    public SimulationEngine(ArrayList<MoveDirection> moves , IWorldMap map, ArrayList<Vector2d> initialPositions) {
        this.animals = new ArrayList<>();
        this.moves = moves;

        for (Vector2d initialPosition : initialPositions) {
            Animal animal = new Animal(map, initialPosition);
            map.place(animal);
            animals.add(animal);
        }
    }

    @Override
    public void run() {
        if (animals.isEmpty())
            return;

        for (MoveDirection direction : moves) {
            moveAnimal(direction);
        }
    }

    protected void moveAnimal(MoveDirection direction) {
        animals.get(animalID).move(direction);
        animalID += 1;
        animalID %= animals.size();
    }

    protected int animalID = 0;
    protected final ArrayList<Animal> animals;
    protected ArrayList<MoveDirection> moves;
}
