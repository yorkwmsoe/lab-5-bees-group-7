package garden;

import javafx.geometry.Point2D;

public interface MovementPattern {

    public Point2D getNextLocation(Point2D currentLoc, Point2D targetLoc, int speed);
}
