package garden.bees;

import javafx.geometry.Point2D;

public class DirectBee extends AbstractBee {

    public DirectBee(Point2D startingLocation) {
        super(10,100, startingLocation);
        String imagePath = "file:bee-1.jpg";
        String name = "Direct Bee";
        this.createView(imagePath, name, startingLocation);
    }

    @Override
    public boolean move() {
        Point2D direction = this.target.getFlowerLocation().subtract(this.currentLocation);
        if(Math.hypot(direction.getX(), direction.getY()) < this.speed) {
            this.currentLocation = this.currentLocation.add(direction);
        } else {
            this.currentLocation = this.currentLocation.add(direction.multiply(speed/Math.hypot(direction.getX(), direction.getY())));
        }
        return super.move();
    }
}
