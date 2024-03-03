package garden;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Flower {
    private int maxEnergy;

    private Pane flowerImageBox;

    private Pane gardenPane;

    private int currentEnergy;

    GardenController garden = new GardenController();

    public Flower(Pane gardenPane) {
        this.gardenPane = gardenPane;
        maxEnergy = 100;
        currentEnergy = 100;
        generateFlowers();
    }

    private void generateFlowers() {
        ImageView flowerImage;
        Label flowerLabel;
        int numFlowers= (int) (Math.random() * 5 + 1); //generates a random number of flowers between 1 and 5
        for (int i = 0; i < numFlowers; i++) {
            VBox flowerImageBox = new VBox();
            //randomly select a flower image to display
            if (i % 2 == 0) {
                flowerImage = new ImageView(new Image("file:flower-2.jpg"));
                flowerLabel = new Label("Pink Flower");
                flowerLabel.setStyle("-fx-text-fill: pink;");
            }
            else {
                flowerImage = new ImageView(new Image("file:flower-1.jpg"));
                flowerLabel = new Label("Yellow Flower");
                flowerLabel.setStyle("-fx-text-fill: yellow;");
            }
            flowerImage.setPreserveRatio(true);
            flowerImage.setFitWidth(75);
            //this sets the image of the flower to a random location within the main pane
            flowerImageBox.setLayoutX((int) (Math.random() * 525));
            flowerImageBox.setLayoutY((int) (Math.random() * 525));

            flowerImageBox.getChildren().addAll(flowerImage, flowerLabel);
            gardenPane.getChildren().add(flowerImageBox);

        }

    }

    public void exchangeEnergy(Bee bee) {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}