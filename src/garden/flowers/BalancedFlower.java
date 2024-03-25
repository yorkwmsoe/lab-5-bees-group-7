package garden.flowers;

import javafx.geometry.Point2D;

public class BalancedFlower extends AbstractFlower {
    public BalancedFlower(Point2D location) {
        super(50, location);
        String imagePath = "file:flower-1.jpg";
        String name = "Balanced Flower";
        this.createView(imagePath, name, location);
    }

    @Override
    public int exchangeEnergy(int beeEnergy) {
        int nectarAmount = beeEnergy - this.currentNectar;
        this.currentNectar += nectarAmount;
        if(currentNectar <= 0) {
            this.currentNectar = 0;
            this.nectarEmpty = true;
        }
        return nectarAmount;
    }
}
