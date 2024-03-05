package garden;
public class Bee {

    private int speed;
    private int maxEnergy;
    private int currentEnergy;
    private Flower target;
    private MovementPattern movementPattern;


    public Bee(int speed, int maxEnergy) {

    }

    public void move() {

    }

    public void die() {

    }

    public void exchangeNectar(int nectar) {
        if(this.maxEnergy < this.currentEnergy + nectar) {
            this.currentEnergy = this.maxEnergy;
        } else if (0 >= this.currentEnergy + nectar) {
            this.currentEnergy = 0;
            this.die();
        } else {
            this.currentEnergy += nectar;
        }
    }
}