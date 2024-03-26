package garden.bees;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class BeeView {

    private final Pane totalView;

    private final ImageView beeImage;

    private final ProgressBar energyBar;

    private final Label beeName;

    private boolean deleted;

    private final double collisionConstant;

    public BeeView(String imagePath, String name, Point2D startingLocation) {
        this.totalView = new VBox();

        this.beeImage = new ImageView(new Image(imagePath));
        this.beeImage.setPreserveRatio(true);
        this.beeImage.setFitWidth(50.0);

        this.beeName = new Label();
        this.beeName.setText(name);
        this.beeName.setStyle("-fx-text-fill: blue;");

        this.energyBar = new ProgressBar();
        this.energyBar.setMaxWidth(50.0);
        this.energyBar.setProgress(1);

        this.totalView.getChildren().add(beeName);
        this.totalView.getChildren().add(beeImage);
        this.totalView.getChildren().add(energyBar);

        this.totalView.setLayoutX(startingLocation.getX());
        this.totalView.setLayoutY(startingLocation.getY());

        this.totalView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                die();
            }
        });

        this.deleted = false;

        this.collisionConstant = 10.0 * Math.sqrt(2);
    }

    public void updateEnergy(double newEnergyRatio) {
        this.energyBar.setProgress(newEnergyRatio);
    }

    public void move(Point2D newLocation) {
        this.totalView.relocate(newLocation.getX(), newLocation.getY());
    }

    public boolean collides(Point2D testPoint) {
        double xDistance = Math.abs(this.totalView.getLayoutX() - testPoint.getX());
        double yDistance = Math.abs(this.totalView.getLayoutY() - testPoint.getY());
        return Math.hypot(xDistance, yDistance) < this.collisionConstant;
    }

    public void die() {
        this.deleted = true;
        this.totalView.getChildren().remove(beeName);
        this.totalView.getChildren().remove(beeImage);
        this.totalView.getChildren().remove(energyBar);
        Pane garden = (Pane) this.totalView.getParent();
        garden.getChildren().remove(this.totalView);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Pane getView() {
        return this.totalView;
    }
}
