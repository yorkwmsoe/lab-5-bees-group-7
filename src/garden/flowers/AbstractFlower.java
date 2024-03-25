package garden.flowers;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public abstract class AbstractFlower {

    protected FlowerView flowerView;
    protected final int maxNectar;
    protected int currentNectar;
    protected boolean nectarEmpty;
    protected Point2D location;

    protected AbstractFlower(int maxNectar, Point2D location) {
        this.maxNectar = maxNectar;
        this.currentNectar = maxNectar;
        this.location = location;
    }

    protected void createView(String imagePath, String name, Point2D location) {
        this.flowerView = new FlowerView(imagePath, name, location);
    }

    public abstract int exchangeEnergy(int beeEnergy);

    public Point2D getFlowerLocation() {
        return this.location;
    }

    public Pane getFlowerView() {
        return this.flowerView.getFlowerView();
    }

    public boolean isDeleted() {
        return this.flowerView.isDeleted();
    }

    public void refillFlower() {
        if(this.nectarEmpty) {
            this.currentNectar++;
            this.flowerView.updateNectarRechargeProgress((currentNectar * 1.0) / maxNectar);
        }
        if(this.currentNectar == this.maxNectar) {
            this.nectarEmpty = false;
        }
        this.flowerView.showNectarRechargeProgress(this.nectarEmpty);
    }
}
