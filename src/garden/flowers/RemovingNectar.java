package garden.flowers;

import garden.flowers.NectarExchanger;

public class RemovingNectar implements NectarExchanger {

    @Override
    public int exchangeNectar(int beeEnergy, int flowerEnergy) {
        //10 percent of the remaining energy from the bee will be taken and given to the flower
        return (int) (beeEnergy * 0.1);
    }
}