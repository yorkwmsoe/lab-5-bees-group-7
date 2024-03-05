package garden;

import javafx.geometry.Point2D;

public class StraightLine implements MovementPattern{
    @Override
    public Point2D getNextLocation(Point2D currentLoc, Point2D targetLoc, int speed) {
        Point2D direction = targetLoc.subtract(currentLoc);
        if(Math.hypot(direction.getX(), direction.getY()) < speed) {
            return direction;
        } else {
            return direction.multiply(speed/Math.hypot(direction.getX(), direction.getY()));
        }
    }


}
