package garden;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GardenController {

    private Pane beeImageBox;               // box containing bee, and it's label; NOT a good domain name!

    private Garden garden;
    private Pane legendImageBox;            // box containing legend
    private double beeXLocation, beeYLocation;  // drawn location of bee; this should be in a domain class

    @FXML
    private Pane theGarden;                 // capture the pane we are drawing on from JavaFX

    private Flower flower;

    private Pane legendPane;


    @FXML
    public void initialize() {              // executed after scene is loaded but before any methods
        // for fun, set up a gradient background; could probably do in SceneBuilder as well
        // note the label has a Z index of 2 so it is drawn above the panel, otherwise it may be displayed "under" the panel and not be visible
        theGarden.setStyle("-fx-background-color: linear-gradient(to bottom right, derive(forestgreen, 20%), derive(forestgreen, -40%));");
        // load image from a file; the file needs to be in the top folder of the project
        ImageView legendImage = new ImageView(new Image("file:legend.jpg")); // draws legend
        legendImage.setPreserveRatio(true);
        legendImage.setFitWidth(150.0);
        legendImageBox = new VBox(legendImage);
        garden = new Garden(theGarden);
        theGarden.setFocusTraversable(true); // ensure garden pane will receive keypresses
    }

    // display the bee at the (beeXLocation, beeYLocation), ensuring the bee does not leave the garden
    private void displayBee() {
        if ( beeXLocation < 0 )
            beeXLocation = 0;
        else if (theGarden.getWidth() > 0 && beeXLocation > theGarden.getWidth() - 10)
            // note: getWidth() is 0 when first load the scene, so don't relocate the bee in that case
            beeXLocation = theGarden.getWidth() - 10;
        if (beeYLocation < 0)
            beeYLocation = 0;
        else if (theGarden.getHeight() > 0 && beeYLocation > theGarden.getHeight() - 10)
            beeYLocation = theGarden.getHeight() - 10;
        beeImageBox.setLayoutX(beeXLocation);
        beeImageBox.setLayoutY(beeYLocation);
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            garden.step();
        }
    }


}