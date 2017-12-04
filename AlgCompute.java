import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class AlgCompute {

    /*number of points in array*/
    private int num;
    /*array of points*/
    private Point[] setOfPoints;
    /*path of the text file*/
    private final Path filePath = Paths.get("E:\\Programare\\Code\\Java\\ClosestPoints\\src\\numbers.txt");
    private Scanner buffer;
    /*calculate the distance calculator*/
    private PointCalculator pCalc;

    public AlgCompute(){

        this.pCalc = new PointCalculator();
        createArray();

        System.out.println(divideAndConquer(setOfPoints, num));
    }

    private double divideAndConquer(Point mainArr[], int n){

        Point px[] = new Point[n];
        Point py[] = new Point[n];

        for (int i = 0; i < n ; i++) {

            px[i] = mainArr[i];
            py[i] = mainArr[i];

        }

        //sort by x
        Arrays.sort(px, Comparator.comparing(Point::getxCoord));
        //sort by y
        Arrays.sort(py, Comparator.comparing(Point::getyCoord));

        return closestPoints(px, py, n);

    }

    private double closestPoints(Point[] px, Point[] py, int n) {

        if(n <= 3){
            return bruteForce(px, n);
        }

        //middle index
        int middle = n/2;
        //middle Point
        Point midPoint = px[middle];

        //Let this be the 'vertical line' separating the arrays

        //array left of point
        Point pyLeft[] = new Point[middle + 2];
        //array right of point
        Point pyRight[] = new Point[n - middle - 1];

        int lInd = 0, rInd = 0; //index of the subarrays

        //populate the subarrays
        for (int i = 0; i < n; i++) {

            if(py[i].getxCoord() <= midPoint.getxCoord()){

                pyLeft[lInd] = py[i];
                lInd++;
            }else {

                pyRight[rInd] = py[i];
                rInd++;
            }

        }

        Point pxLeft[] = new Point[middle + 1];
        Point pxRight[] = new Point[n - middle - 1];

        lInd = 0; rInd = 0;

        /*sketch*/
        for (int i = 0; i < n; i++) {

            if(i <= middle){

                pxLeft[lInd] = px[i];
                lInd++;
            }else {

                pxRight[rInd] = px[i];
                rInd++;
            }

        }

        double distLeft = closestPoints(pxLeft, pyLeft, middle);

        double distRight = closestPoints(pxRight, pyRight, n - middle);

        double dist = Math.min(distLeft, distRight);
        Point strip[] = new Point[n];

        int contor = 0;
        for (int i = 0; i < n ; i++) {
            if(Math.abs(py[i].getxCoord() - midPoint.getxCoord()) < dist){
                strip[contor] = py[i];
                contor++;
            }
        }

        return Math.min(dist, stripClosest(strip, contor, dist));


    }

    private double stripClosest(Point stripTemp[], int n, double d){

        double min = d;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n && (stripTemp[j].getyCoord() - stripTemp[i].getyCoord()) < min; j++) {

                if(pCalc.getDistance(stripTemp[i], stripTemp[j]) < min){
                    min = pCalc.getDistance(stripTemp[i], stripTemp[j]);
                }
            }
        }

        return min;

    }

    /*implementation of the brute force algorithm for when  n <= 3*/
    private double bruteForce(Point tempPoint[], int n){

        double min = Double.MAX_VALUE;

        for (int i = 0; i < n-1; i++) {
            for (int j = i + 1; j < n-1 ; j++) {
                if(pCalc.getDistance(tempPoint[i], tempPoint[j]) < min){
                    min = pCalc.getDistance(tempPoint[i], tempPoint[j]);
                }
            }
        }

        return min;

    }

    /*Read file and create the array of points*/
    private void createArray(){

        try {

            buffer = new Scanner(filePath);

            num = buffer.nextInt();

            setOfPoints = new Point[num];

            for(int i  = 0; i < num; i++){

                setOfPoints[i] = new Point(buffer.nextInt(), buffer.nextInt());
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


}
