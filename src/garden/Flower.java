package garden;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Flower {
    private int maxEnergy;

    private Pane flowerImageBox;

    private NectarExchanger nectarExchanger;

    private Pane gardenPane;

    private int currentEnergy;

    private final int xOffset = 150;
    private final int yOffset = 137;

    public Flower(Pane gardenPane, NectarExchanger nectarExchanger) {
        this.nectarExchanger = nectarExchanger;
        this.gardenPane = gardenPane;
        maxEnergy = 100;
        currentEnergy = 100;
        generateFlower();
    }

    private void generateFlower() {
        ImageView flowerImage;
        Label flowerLabel;
        VBox flowerImageBox = new VBox();
        //generate number between 1 and 5
        int i = (int) (Math.random() * 5) + 1;
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
        flowerImageBox.setLayoutX((int) (Math.random() * 300) + xOffset);
        flowerImageBox.setLayoutY((int) (Math.random() * 300) + yOffset);
        flowerImageBox.getChildren().addAll(flowerImage, flowerLabel);
        gardenPane.getChildren().add(flowerImageBox);


    }

    public int exchangeEnergy(int beeEnergy) {
        int nectarAmount = nectarExchanger.exchangeNectar(beeEnergy, currentEnergy);
        currentEnergy -= nectarAmount;
        return nectarAmount;
    }

    public Point2D getFlowerLocation() {
        return new Point2D(flowerImageBox.getLayoutX(), flowerImageBox.getLayoutY());
    }




}