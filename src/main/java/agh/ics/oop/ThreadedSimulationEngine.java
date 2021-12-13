package agh.ics.oop;

import agh.ics.oop.gui.IMapChangeObserver;
import javafx.application.Platform;

import java.util.ArrayList;

public class ThreadedSimulationEngine extends SimulationEngine {
    public void setDirections(ArrayList<MoveDirection> moves) {
        this.moves = moves;
    }

    public ThreadedSimulationEngine(ArrayList<MoveDirection> moves, IWorldMap map, ArrayList<Vector2d> initialPositions, IMapChangeObserver mapChangeObserver) {
        super(moves, map, initialPositions);
        this.mapChangeObserver = mapChangeObserver;
    }

    @Override
    public void run() {
        Platform.runLater(() -> mapChangeObserver.onMapChanged());

        if (animals.isEmpty())
            return;

        for (MoveDirection direction : moves) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            moveAnimal(direction);

            Platform.runLater(() -> mapChangeObserver.onMapChanged());
        }
    }

    private IMapChangeObserver mapChangeObserver;
}
