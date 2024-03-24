package garden;

import garden.bees.*;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Garden {
    private final Set<Flower> flowerBed;
    private final Set<BasicBeeController> beeHive;


    public Garden(Pane gardenPane) {
        this.flowerBed= new HashSet<>();
        int offset = 50;
        for(int i = 1; i < 6; i++) {
            NectarExchanger flowerNectar = i % 2 == 0? new RemovingNectar() : new NectarGiving();
            flowerBed.add(new Flower(gardenPane, flowerNectar, offset * i));
        }

        this.beeHive = new HashSet<>();
        BasicBeeController straightLineBee = new DirectBee(new Point2D(200, 200));
        straightLineBee.setNewTarget(this.getNextFlowerTarget());
        BasicBeeController manhattanBee = new ManhattanBee(new Point2D(400, 400));
        manhattanBee.setNewTarget(this.getNextFlowerTarget());
        this.beeHive.add(straightLineBee);
        this.beeHive.add(manhattanBee);
        gardenPane.getChildren().add(straightLineBee.getBeeView().getView());
        gardenPane.getChildren().add(manhattanBee.getBeeView().getView());
    }

    public Flower getNextFlowerTarget() {
        int flower = new Random().nextInt(flowerBed.size());
        int i = 0;
        Flower target = (Flower) flowerBed.toArray()[0];
        for(Flower f : flowerBed) {
            if(i == flower) {
                return f;
            }
            i++;
        }
        return target;
    }

    public void step() {
        for(BasicBeeController bee: beeHive) {
            if(bee.move()) {
                bee.setNewTarget(getNextFlowerTarget());
            }
        }
        for(Flower flower: flowerBed) {
            flower.refillFlower();
        }
    }
}