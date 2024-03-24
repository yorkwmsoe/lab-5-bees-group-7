package garden.bees;

import garden.Flower;
import javafx.geometry.Point2D;

public abstract class BasicBeeController {

    protected BeeView beeView;
    protected Flower target;
    protected final int speed;
    protected final int maxEnergy;
    protected int currentEnergy;
    protected boolean dead;

    protected Point2D currentLocation;

    protected BasicBeeController(int speed, int maxEnergy, Point2D startingLocation) {
        this.speed = speed;
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
        this.dead = false;
        this.currentLocation = startingLocation;
    }

    protected void createView(String imagePath, String name, Point2D startingLocation) {
        this.beeView = new BeeView(imagePath, name, startingLocation);
    }

    public boolean move() {
        this.beeView.move(this.currentLocation);
        if(this.beeView.collides(this.target)) {
            this.exchangeNectar();
            return true;
        } else {
            return false;
        }
    };

    public void die() {
        this.dead = true;
        this.getBeeView().die();
        this.target = null;
    }

    public void setNewTarget(Flower newTarget) {
        this.target = newTarget;
    }

    public void exchangeNectar() {
        if(!this.beeView.collides(this.target)) {
            return;
        }
        int nectar = this.target.exchangeEnergy(this.currentEnergy);
        if(this.maxEnergy < this.currentEnergy + nectar) {
            this.currentEnergy = this.maxEnergy;
            this.beeView.updateEnergy(1);
        } else if (0 >= this.currentEnergy + nectar) {
            this.currentEnergy = 0;
            this.die();
        } else {
            this.currentEnergy += nectar;
            this.beeView.updateEnergy((double) this.currentEnergy / this.maxEnergy);
        }
    }

    public BeeView getBeeView() {
        return beeView;
    }
}