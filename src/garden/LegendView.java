package garden;

import garden.bees.BeeType;
import garden.flowers.FlowerType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LegendView {
    private final Pane totalView;
    private final Garden garden;



    public LegendView(Garden garden) {
        this.garden = garden;
        this.totalView = new VBox();
        createFlowerLegend();
        createBeeLegend();
        this.totalView.setPrefHeight(600.0);
        this.totalView.setPrefWidth(150.0);
        this.totalView.setStyle("-fx-background-color: grey");

    }

    public Pane getView() {
        return this.totalView;
    }

    private void createFlowerLegend() {
        Pane flowerLegend = new VBox();
        flowerLegend.setMaxWidth(150.0);

        flowerLegend.getChildren().add(createFlowerInfo(FlowerType.BALANCED));
        flowerLegend.getChildren().add(createFlowerInfo(FlowerType.GENEROUS));
        flowerLegend.getChildren().add(createFlowerInfo(FlowerType.GREEDY));

        this.totalView.getChildren().add(flowerLegend);
    }

    private Pane createFlowerInfo(FlowerType flowerType) {
        Pane flowerInfo = new VBox();
        Label flowerName = new Label();
        ImageView flowerPicture = null;
        Label flowerDescription = new Label();

        switch (flowerType) {
            case BALANCED -> {
                flowerName.setText("Balanced Flower");
                flowerPicture = new ImageView(new Image("file:flower-1.jpg"));
                flowerDescription.setText("Flower that balances \nnectar with the bee\n\n");
            }
            case GENEROUS -> {
                flowerName.setText("Generous Flower");
                flowerPicture = new ImageView(new Image("file:flower-2.jpg"));
                flowerDescription.setText("Flower that gives as much \nnectar as it can\n\n");
            }
            case GREEDY -> {
                flowerName.setText("Greedy Flower");
                flowerPicture = new ImageView(new Image("file:flower-3.jpg"));
                flowerDescription.setText("Flower that takes as much \nnectar as it can\n\n");
            }

        }
        flowerPicture.setPreserveRatio(true);
        flowerPicture.setFitWidth(50.0);
        flowerPicture.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                garden.addFlower(flowerType);
            }
        });
        flowerInfo.getChildren().addAll(flowerName, flowerPicture, flowerDescription);
        return flowerInfo;
    }

    private void createBeeLegend() {
        Pane beeLegend = new VBox();
        beeLegend.setMaxWidth(150.0);

        beeLegend.getChildren().add(createBeeInfo(BeeType.DIRECT));
        beeLegend.getChildren().add(createBeeInfo(BeeType.MANHATTAN));
    }

    private Pane createBeeInfo(BeeType beeType) {
        Pane beeInfo = new VBox();
        Label beeName = new Label();
        ImageView beeImage = null;
        Label beeDesc = new Label();
        switch (beeType) {
            case DIRECT -> {
                beeName.setText("Direct Bee");
                beeImage = new ImageView(new Image("file:bee-1.jpg"));
                beeDesc.setText("Bee that moves in a \nstraight line to target flower\n\n");
            }
            case MANHATTAN -> {
                beeName.setText("Manhattan Bee");
                beeImage = new ImageView(new Image("file:bee-1.jpg"));
                beeDesc.setText("Bee that only moves in X \nor Y axis at a time \n(Manhattan Movement)\n\n");
            }
        }
        beeImage.setPreserveRatio(true);
        beeImage.setFitWidth(50.0);
        beeImage.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                garden.addBee(beeType);
            }
        });
        beeInfo.getChildren().addAll(beeName, beeImage, beeDesc);
        return beeInfo;
    }
}
