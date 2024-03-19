package garden;
public class NectarGiving implements NectarExchanger {

    @Override
    public int exchangeNectar(int beeEnergy, int flowerEnergy) {
        if (flowerEnergy > 0) {
            return flowerEnergy;
        }
        return -1;
    }
}