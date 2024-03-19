package garden;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Bee {

    private final int speed;
    private final int maxEnergy;
    private int currentEnergy;
    private Point2D currentLocation;
    private Flower target;
    private final MovementPattern movementPattern;
    private final Pane beeView;
    private final ProgressBar energyBar;

    public Bee(int speed, int maxEnergy, Flower firstTarget, MovementPattern movementPattern) {
        this.speed = speed;
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
        this.target = firstTarget;
        this.movementPattern = movementPattern;
        ImageView beeImage = new ImageView(new Image("file:bee-1.jpg"));
        beeImage.setPreserveRatio(true);
        beeImage.setFitWidth(50.0);
        Label beeName = new Label();
        this.energyBar = new ProgressBar();
        this.energyBar.setMaxWidth(50.0);
        this.energyBar.setProgress(1);
        beeName.setText("Some Bee");
        beeName.setStyle("-fx-text-fill: blue;");
        beeView = new VBox();
        beeView.getChildren().add(beeImage);
        beeView.getChildren().add(beeName);
        beeView.getChildren().add(energyBar);
        beeView.setLayoutY(200);
        beeView.setLayoutX(100);
        this.currentLocation = new Point2D(200.0, 100.0);
    }

    public Pane getBeeView() {
        return beeView;
    }

    public boolean move() {
        Point2D newLocation = this.movementPattern.getNextLocation(this.currentLocation, this.target.getFlowerLocation(), this.speed);
        currentLocation = currentLocation.add(newLocation);
        this.beeView.relocate(currentLocation.getX(), currentLocation.getY());
        this.currentEnergy--;
        this.energyBar.setProgress((this.currentEnergy * 1.0) / this.maxEnergy);
        if(currentEnergy <= 0) {
            this.die();
        }
        return false;
    }

    public void die() {
        this.beeView.setVisible(false);
    }

    public void setNewTarget(Flower newTarget) {
        this.target = newTarget;
    }

    public void exchangeNectar() {
        int nectar = this.target.exchangeEnergy(this.currentEnergy);
        if(this.maxEnergy < this.currentEnergy + nectar) {
            this.currentEnergy = this.maxEnergy;
        } else if (0 >= this.currentEnergy + nectar) {
            this.currentEnergy = 0;
            this.die();
        } else {
            this.currentEnergy += nectar;
        }
    }
}