package garden;

import garden.bees.*;
import garden.flowers.*;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Garden {

    private final Pane gardenPane;
    private final Set<AbstractFlower> flowerBed;
    private final Set<AbstractBee> beeHive;
    private Point2D lastFlowerLocation;
    private Point2D lastBeeLocation;


    public Garden(Pane gardenPane) {
        this.gardenPane = gardenPane;
        this.flowerBed= new HashSet<>();
        this.lastFlowerLocation = new Point2D(0, 0);
        this.lastBeeLocation = new Point2D(0, 0);
        for (int i = 0; i < 6; i++) {
            if(i % 3 == 0) {
                this.addFlower(FlowerType.BALANCED);
            } else if (i % 3 == 1){
                this.addFlower(FlowerType.GENEROUS);
            } else {
                this.addFlower(FlowerType.GREEDY);
            }
        }
        this.flowerBed.remove(null);

        this.beeHive = new HashSet<>();
        this.addBee(BeeType.DIRECT);
        this.addBee(BeeType.MANHATTAN);

        this.beeHive.remove(null);
    }

    public void addFlower(FlowerType flowerType) {
        double slice = 60;
        double x = (Math.random() * (Math.abs((slice * flowerBed.size()) - lastFlowerLocation.getX()))) + lastFlowerLocation.getX();
        double y = (Math.random() * (Math.abs((slice * flowerBed.size()) - lastFlowerLocation.getY()))) + lastFlowerLocation.getY();
        if(x > 450) {
            x = 450;
        }
        if(y > 600) {
            y = 600;
        }

        Point2D newFlowerLocation = new Point2D(x, y);
        lastFlowerLocation = newFlowerLocation;
        AbstractFlower newFlower;
        if(flowerType.equals(FlowerType.BALANCED)) {
            newFlower = new BalancedFlower(newFlowerLocation);
            this.gardenPane.getChildren().add(newFlower.getFlowerView());
        } else if(flowerType.equals(FlowerType.GENEROUS)) {
            newFlower = new GenerousFlower(newFlowerLocation);
            this.gardenPane.getChildren().add(newFlower.getFlowerView());
        } else if (flowerType.equals(FlowerType.GREEDY)) {
            newFlower = new GreedyFlower(newFlowerLocation);
            this.gardenPane.getChildren().add(newFlower.getFlowerView());
        } else {
            newFlower = null;
        }
        this.flowerBed.add(newFlower);
    }

    public void addBee(BeeType beeType) {
        double slice = 100;
        double x = (Math.random() * ((slice * beeHive.size()) - lastBeeLocation.getX())) + lastBeeLocation.getX();
        double y = (Math.random() * ((slice * beeHive.size()) - lastBeeLocation.getY())) + lastBeeLocation.getY();
        if(x > 450) {
            x = 450;
        }
        if(y > 600) {
            y = 600;
        }

        Point2D newBeeLocation = new Point2D(x, y);
        lastBeeLocation = newBeeLocation;
        AbstractBee newBee;
        if(beeType.equals(BeeType.DIRECT)) {
            newBee = new DirectBee(newBeeLocation);
            newBee.setNewTarget(this.getNextFlowerTarget());
            this.gardenPane.getChildren().add(newBee.getBeeView());
        } else if (beeType.equals(BeeType.MANHATTAN)) {
            newBee = new ManhattanBee(newBeeLocation);
            newBee.setNewTarget(this.getNextFlowerTarget());
            this.gardenPane.getChildren().add(newBee.getBeeView());
        } else {
            newBee = null;
        }
        beeHive.add(newBee);

    }

    public AbstractFlower getNextFlowerTarget() {
        int flower = new Random().nextInt(flowerBed.size());
        int i = 0;
        AbstractFlower target = (AbstractFlower) flowerBed.toArray()[0];
        for(AbstractFlower f : flowerBed) {
            if(i == flower) {
                return f;
            }
            i++;
        }
        return target;
    }

    public void step() {
        Set<AbstractBee> deadBees = new HashSet<>();
        for(AbstractBee bee: beeHive) {
            if(bee.isDead()) {
                deadBees.add(bee);
            } else if(bee.move()) {
                bee.setNewTarget(getNextFlowerTarget());
            }
        }
        this.beeHive.removeAll(deadBees);
        Set<AbstractFlower> deletedFlowers = new HashSet<>();
        for(AbstractFlower flower: flowerBed) {
            if(flower.isDeleted()) {
                deletedFlowers.add(flower);
            } else {
                flower.refillFlower();
            }
        }
        this.flowerBed.removeAll(deletedFlowers);
    }
}