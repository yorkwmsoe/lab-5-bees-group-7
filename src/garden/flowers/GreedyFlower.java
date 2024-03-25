package garden.flowers;

import javafx.geometry.Point2D;

public class GreedyFlower extends AbstractFlower {
    public GreedyFlower(Point2D location) {
        super(150, location);
        String imagePath = "file:flower-3.jpg";
        String name = "Greedy Flower";
        this.createView(imagePath, name, location);
    }

    @Override
    public int exchangeEnergy(int beeEnergy) {
        int nectarAmount;
        if(this.currentNectar <= 0 || this.currentNectar / 10 == 0) {
            nectarAmount = -(beeEnergy - (beeEnergy / 10));
        } else if (beeEnergy / 10 <= 0) {
            nectarAmount = this.currentNectar / 10;
        } else {
            nectarAmount = -beeEnergy / 10;
        }
        this.currentNectar -= nectarAmount;
        if(currentNectar <= 0) {
            this.currentNectar = 0;
            this.nectarEmpty = true;
        }
        return nectarAmount;
    }
}
