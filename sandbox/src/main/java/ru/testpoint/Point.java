package ru.testpoint;

public class Point {

    public double x, y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(((p2.x - p1.x)*(p2.x - p1.x))+((p2.y-p1.y)*(p2.y-p1.y)));
  }
}
