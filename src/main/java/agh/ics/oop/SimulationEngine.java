package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {
    public SimulationEngine(ArrayList<MoveDirection> moves , IWorldMap map, ArrayList<Vector2d> initialPositions) {
        this.map = map;
        this.animals = new ArrayList<>();
        this.moves = moves;

        for (Vector2d initialPosition : initialPositions) {
            Animal animal = new Animal(map, initialPosition);
            if (map.place(animal))
                animals.add(animal);
        }
    }

    @Override
    public void run() {
        if (animals.isEmpty())
            return;

        int animalID = 0;
        for (MoveDirection direction : moves) {
            animals.get(animalID).move(direction);
            animalID += 1;
            animalID %= animals.size();
        }


    }

    private final IWorldMap map;
    private final ArrayList<Animal> animals;
    private final ArrayList<MoveDirection> moves;
}
