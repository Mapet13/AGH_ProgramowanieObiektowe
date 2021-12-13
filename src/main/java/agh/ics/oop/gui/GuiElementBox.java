package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;

public class GuiElementBox {
  private static final int size = 20;
  public final VBox box = new VBox();

  public GuiElementBox(IMapElement obj) {
    try {
      ImageView img = new ImageView(new Image(new FileInputStream(obj.getImagePath())));
      img.setFitWidth(size);
      img.setFitHeight(size);

      box.setAlignment(Pos.BASELINE_CENTER);
      box.getChildren().add(ElementKind.Image.value, img);
    } catch (Exception e) {
      e.printStackTrace();
    }

    box.getChildren().add(ElementKind.Name.value, new Label(obj.getName()));
  }

  private enum ElementKind {
    Image(0),
    Name(1);

    public final int value;

    ElementKind(int value) {
      this.value = value;
    }
  }

}
