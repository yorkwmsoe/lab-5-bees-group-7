package garden.bees;

import garden.flowers.AbstractFlower;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public abstract class AbstractBee {

    protected BeeView beeView;
    protected AbstractFlower target;
    protected final int speed;
    protected final int maxEnergy;
    protected int currentEnergy;
    protected boolean dead;

    protected Point2D currentLocation;

    protected AbstractBee(int speed, int maxEnergy, Point2D startingLocation) {
        this.speed = speed;
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
        this.dead = false;
        this.currentLocation = startingLocation;
    }

    protected void createView(String imagePath, String name, Point2D startingLocation) {
        this.beeView = new BeeView(imagePath, name, startingLocation);
    }

    public boolean isDead() {
        return dead;
    }

    public boolean move() {
        if(beeView.isDeleted()) {
            this.delete();
            return false;
        }
        this.currentEnergy--;
        if(this.currentEnergy <= 0) {
            this.die();
            return false;
        }
        this.beeView.updateEnergy(this.currentEnergy * 1.0 / this.maxEnergy);
        this.beeView.move(this.currentLocation);
        if(this.beeView.collides(this.target.getFlowerLocation())) {
            this.exchangeNectar();
            return true;
        } else {
            return false;
        }
    }

    public void delete() {
        this.dead = true;
        this.target = null;
    }

    public void die() {
        this.delete();
        this.beeView.die();
    }

    public void setNewTarget(AbstractFlower newTarget) {
        this.target = newTarget;
    }

    public void exchangeNectar() {
        if(!this.beeView.collides(this.target.getFlowerLocation())) {
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

    public Pane getBeeView() {
        return this.beeView.getView();
    }
}