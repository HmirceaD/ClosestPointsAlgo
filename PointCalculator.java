
public class PointCalculator {

    public double getDistance(Point p1, Point p2){

        return Math.sqrt(((p1.xCoord - p2.xCoord) * (p1.xCoord - p2.xCoord)) + ((p1.yCoord - p2.yCoord) * (p1.yCoord - p2.yCoord)));

    }

}
