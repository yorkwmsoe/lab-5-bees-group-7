package garden.flowers;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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

    private ProgressIndicator progressIndicator;
    private int xOffset;
    private int yOffset;

    private int offset;

    private boolean flowerEmpty;

    public Flower(Pane gardenPane, NectarExchanger nectarExchanger, int offset) {
        this.nectarExchanger = nectarExchanger;
        this.gardenPane = gardenPane;
        xOffset = 150;
        yOffset = 137;
        this.offset = offset;
        maxEnergy = 100;
        currentEnergy = 100;
        flowerEmpty = false;
        generateFlower();
    }

    private void generateFlower() {
        progressIndicator = new ProgressIndicator(0);
        progressIndicator.setVisible(false);
        ImageView flowerImage;
        Label flowerLabel;
        this.flowerImageBox = new VBox();
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

        this.flowerImageBox.setLayoutX((int) (Math.random() * offset) + xOffset);
        this.flowerImageBox.setLayoutY((int) (Math.random() * offset) + yOffset);
        this.flowerImageBox.getChildren().addAll(progressIndicator, flowerImage, flowerLabel);
        gardenPane.getChildren().add(this.flowerImageBox);


    }

    public int exchangeEnergy(int beeEnergy) {
        int nectarAmount = nectarExchanger.exchangeNectar(beeEnergy, currentEnergy);
        currentEnergy -= nectarAmount;
        if (currentEnergy <= 0) {
            flowerEmpty = true;
            currentEnergy = 0;
            progressIndicator.setVisible(true);
        }
        return nectarAmount;
    }

    public Point2D getFlowerLocation() {
        return new Point2D(this.flowerImageBox.getLayoutX(), this.flowerImageBox.getLayoutY());
    }

    public void refillFlower() {
        if (flowerEmpty) {
            currentEnergy++;
            progressIndicator.setProgress((currentEnergy * 1.0) / maxEnergy);
        }
        if(currentEnergy == maxEnergy) {
            flowerEmpty = false;
            progressIndicator.setVisible(false);
        }
    }




}