
public class Point {

    public int xCoord;
    public int yCoord;

    public Point(){

        this.xCoord = 0;
        this.yCoord = 0;
    }

    public Point(int x, int y){

        this.xCoord = x;
        this.yCoord = y;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public String toString() {

        return "(" + this.xCoord + "," + this.yCoord + ")";
    }
}
