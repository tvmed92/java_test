package ru.stqa.pft.sangbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double distance(Point p1, Point p2) {
        double xdist = p2.getX() - p1.getX();
        double ydist = p2.getY() - p1.getY();
        return Math.sqrt(xdist * xdist + ydist * ydist);
    }
}
