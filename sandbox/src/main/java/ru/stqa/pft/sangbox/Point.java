package ru.stqa.pft.sangbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        double xdist = this.x - p.x;
        double ydist = this.y - p.y;
        return Math.sqrt(xdist * xdist + ydist * ydist);
    }
}
