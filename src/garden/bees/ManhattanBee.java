package garden.bees;

import javafx.geometry.Point2D;

public class ManhattanBee extends AbstractBee {
    public ManhattanBee(Point2D startingLocation) {
        super(25, 50, startingLocation);
        String imagePath = "file:bee-2.jpg";
        String name = "Manhattan Bee";
        this.createView(imagePath, name, startingLocation);
    }

    @Override
    public boolean move() {
        Point2D direction;
        if(Math.abs(this.target.getFlowerLocation().getX() - this.currentLocation.getX()) * speed >= (Math.abs(this.target.getFlowerLocation().getY() - this.currentLocation.getY()))) {
            direction = new Point2D(this.target.getFlowerLocation().getX() - this.currentLocation.getX(),0);
        } else {
            direction = new Point2D(0, this.target.getFlowerLocation().getY() - this.currentLocation.getY());
        }
        if(Math.hypot(direction.getX(), direction.getY()) < speed) {
            this.currentLocation = this.currentLocation.add(direction);
        } else {
            this.currentLocation = this.currentLocation.add(direction.multiply(speed/Math.hypot(direction.getX(), direction.getY())));
        }
        return super.move();
    }
}
