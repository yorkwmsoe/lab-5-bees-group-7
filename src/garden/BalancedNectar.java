package garden;
public class BalancedNectar implements  NectarExchanger {

    @Override
    public int exchangeNectar(int beeEnergy, int flowerEnergy) {
        return flowerEnergy - beeEnergy;
    }
}