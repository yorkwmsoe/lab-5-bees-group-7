package garden;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GardenController {

    private Garden garden;

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX
    private LegendView legendView;


    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project
        Pane gardenArea = new Pane();
        gardenArea.setPrefWidth(450.0);
        gardenArea.setPrefHeight(600.0);
        garden = new Garden(gardenArea);
        gardenArea.getChildren().add(makeInstructions());
        legendView = new LegendView(garden);
        theGarden.getChildren().add(legendView.getView());
        theGarden.getChildren().add(gardenArea);
        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            garden.step();
        }
    }

    private Label makeInstructions() {
        Label instructions = new Label("Press arrow keys to move bee");
        instructions.setLayoutX(50.0);
        instructions.setLayoutY(550.0);
        instructions.setScaleZ(2.0);
        instructions.setTextFill(Paint.valueOf("darkred"));
        instructions.setTranslateZ(1.0);
        instructions.setFont(new Font("Poor Richard", 18.0));
        return instructions;
    }


}