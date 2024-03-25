package garden.flowers;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FlowerView {
    private final Pane flowerView;
    private final ProgressIndicator nectarRechargeIndicator;
    private final ImageView flowerImage;
    private final Label flowerName;
    private boolean deleted;
    public FlowerView(String imagePath, String name, Point2D startingLocation) {
        this.flowerView = new VBox();

        this.nectarRechargeIndicator = new ProgressIndicator(0);
        this.nectarRechargeIndicator.setVisible(false);

        this.flowerImage = new ImageView(new Image(imagePath));
        this.flowerImage.setPreserveRatio(true);
        this.flowerImage.setFitWidth(75);

        this.flowerName = new Label(name);

        this.flowerView.setLayoutX(startingLocation.getX());
        this.flowerView.setLayoutY(startingLocation.getY());

        this.flowerView.getChildren().addAll(this.nectarRechargeIndicator, this.flowerImage, this.flowerName);

        this.deleted = false;

        this.flowerView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    flowerView.setVisible(false);
                    flowerView.setLayoutX(-100);
                    flowerView.setLayoutY(-100);
                    flowerView.getChildren().remove(nectarRechargeIndicator);
                    flowerView.getChildren().remove(flowerImage);
                    flowerView.getChildren().remove(flowerName);
                    Pane garden = (Pane) flowerView.getParent();
                    garden.getChildren().remove(flowerView);
                    deleted = true;
                }
            }
        });
    }

    public Pane getFlowerView() {
        return this.flowerView;
    }

    public void showNectarRechargeProgress(boolean willShowNectarIndicator) {
        this.nectarRechargeIndicator.setVisible(willShowNectarIndicator);
    }

    public void updateNectarRechargeProgress(double newNectarRatio) {
        this.nectarRechargeIndicator.setProgress(newNectarRatio);
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}
