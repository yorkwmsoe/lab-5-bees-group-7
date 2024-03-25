package garden.flowers;

import javafx.geometry.Point2D;

public class GenerousFlower extends AbstractFlower {
    public GenerousFlower(Point2D location) {
        super(75, location);
        String imagePath = "file:flower-2.jpg";
        String name = "Generous Flower";
        this.createView(imagePath, name, location);
    }

    @Override
    public int exchangeEnergy(int beeEnergy) {
        int nectarAmount;
        if(this.currentNectar <= 0) {
            nectarAmount = -beeEnergy / 10;
        } else if (this.currentNectar / 10 <= 0) {
            nectarAmount = 1;
        } else {
            nectarAmount = this.currentNectar / 10;
        }
        this.currentNectar -= nectarAmount;
        if(currentNectar <= 0) {
            this.currentNectar = 0;
            this.nectarEmpty = true;
        }
        return nectarAmount;
    }
}
