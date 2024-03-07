package garden;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Garden {
    private final Set<Flower> flowerBed;

    private Set<Bee> beeHive;


    public Garden(Pane gardenPane) {
        this.flowerBed= new HashSet<>();
        for(int i = 0; i < 5; i++) {
            NectarExchanger flowerNectar = i % 2 == 0? new BalancedNectar() : new NectarGiving();
            flowerBed.add(new Flower(gardenPane, flowerNectar));
        }
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}