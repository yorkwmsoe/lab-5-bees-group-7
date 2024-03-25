package garden;

import garden.bees.BeeType;
import garden.flowers.FlowerType;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LegendView {
    Pane totalView;
    Pane flowerLegend;
    Pane beeLegend;
    Garden garden;


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
        this.flowerLegend = new VBox();
        this.flowerLegend.setMaxWidth(150.0);

        Label balancedFlowerName = new Label("Balanced Flower");
        ImageView balancedFlowerImage = new ImageView(new Image("file:flower-1.jpg"));
        balancedFlowerImage.setPreserveRatio(true);
        balancedFlowerImage.setFitWidth(50.0);
        balancedFlowerImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    garden.addFlower(FlowerType.BALANCED);
                }
            }
        });
        Label balancedFlowerDesc = new Label("Flower that balances \nnectar with the bee\n\n");
        flowerLegend.getChildren().addAll(balancedFlowerName, balancedFlowerImage, balancedFlowerDesc);

        Label generousFlowerName = new Label("Generous Flower");
        ImageView generousFlowerImage = new ImageView(new Image("file:flower-2.jpg"));
        generousFlowerImage.setPreserveRatio(true);
        generousFlowerImage.setFitWidth(50.0);
        generousFlowerImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    garden.addFlower(FlowerType.GENEROUS);
                }
            }
        });
        Label generousFlowerDesc = new Label("Flower that gives as much \nnectar as it can\n\n");
        this.flowerLegend.getChildren().addAll(generousFlowerName, generousFlowerImage, generousFlowerDesc);

        Label greedyFlowerName = new Label("Greedy Flower");
        ImageView greedyFlowerImage = new ImageView(new Image("file:flower-3.jpg"));
        greedyFlowerImage.setPreserveRatio(true);
        greedyFlowerImage.setFitWidth(50.0);
        greedyFlowerImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    garden.addFlower(FlowerType.GREEDY);
                }
            }
        });
        Label greedyFlowerDesc = new Label("Flower that takes as much \nnectar as it can\n\n");
        this.flowerLegend.getChildren().addAll(greedyFlowerName, greedyFlowerImage, greedyFlowerDesc);
        this.totalView.getChildren().add(this.flowerLegend);
    }

    private void createBeeLegend() {
        this.beeLegend = new VBox();
        this.beeLegend.setMaxWidth(150.0);

        Label directBeeName = new Label("Direct Bee");
        ImageView directBeeImage = new ImageView(new Image("file:bee-1.jpg"));
        directBeeImage.setPreserveRatio(true);
        directBeeImage.setFitWidth(50.0);
        directBeeImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    garden.addBee(BeeType.DIRECT);
                }
            }
        });
        Label directBeeDesc = new Label("Bee that moves in a \nstraight line to target flower\n\n");
        this.beeLegend.getChildren().addAll(directBeeName, directBeeImage, directBeeDesc);

        Label manhattanBeeName = new Label("Manhattan Bee");
        ImageView manhattanBeeImage = new ImageView(new Image("file:bee-2.jpg"));
        manhattanBeeImage.setPreserveRatio(true);
        manhattanBeeImage.setFitWidth(50.0);
        manhattanBeeImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    garden.addBee(BeeType.MANHATTAN);
                }
            }
        });
        Label manhattanBeeDesc = new Label("Bee that only moves in X \nor Y axis at a time \n(Manhattan Movement)\n\n");
        this.beeLegend.getChildren().addAll(manhattanBeeName, manhattanBeeImage, manhattanBeeDesc);
        this.totalView.getChildren().add(beeLegend);
    }
}
