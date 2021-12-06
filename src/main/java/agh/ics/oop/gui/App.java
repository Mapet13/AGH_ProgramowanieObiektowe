package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        grid = new GridPane();
        grid.setGridLinesVisible(true);

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        int colHeight = height / (2 + upperRight.y - lowerLeft.y);
        for (int i = lowerLeft.y; i <= upperRight.y + 1; i++) {
            grid.getRowConstraints().add(new RowConstraints(colHeight));
        }

        int colWidth = width / (2 + upperRight.x - lowerLeft.x);
        for (int i = lowerLeft.x; i <= upperRight.x + 1; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(colWidth));
        }

        drawHeader(lowerLeft, upperRight);
        for (int i = upperRight.y; i >= lowerLeft.y; i--) {
            addToGrid(String.format("%d", i), 0, upperRight.y - i + 1);
            for (int j = lowerLeft.x; j <= upperRight.x; j++) {
                drawObject(new Vector2d(j, i), upperRight, lowerLeft);
            }

        }

        Scene scene = new Scene(grid, width, height);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() {
        ArrayList<MoveDirection> directions = new OptionsParser().parse(getParameters().getRaw());
        map = new GrassField(10);
        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    private void drawHeader(Vector2d lowerLeft, Vector2d upperRight) {
        addToGrid("y\\x", 0, 0);
        for (int j = lowerLeft.x; j <= upperRight.x; j++) {
            addToGrid(String.format("%d", j), j - lowerLeft.x + 1, 0);
        }
    }

    private void drawObject(Vector2d pos, Vector2d upperRight, Vector2d lowerLeft) {
        if (this.map.isOccupied(pos)) {
            Object object = this.map.objectAt(pos);
            if (object != null) {
                addToGrid(object.toString(), pos.x - lowerLeft.x + 1, upperRight.y - pos.y + 1);
            }
        }
    }

    private void addToGrid(String text, int col, int row) {
        Label label = new Label(text);
        grid.add(label, col, row);
        GridPane.setHalignment(label, HPos.CENTER);
    }


    private GridPane grid;
    private AbstractWorldMap map;

    private final int width = 400;
    private final int height = 400;
}
