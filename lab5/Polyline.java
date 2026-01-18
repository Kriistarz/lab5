package lab5;

import java.util.Arrays;

public class Polyline {
    private Point[] points;

    public Polyline(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() { return points; }

    @Override
    public String toString() {
        return "Линия " + Arrays.toString(points);
    }
}
