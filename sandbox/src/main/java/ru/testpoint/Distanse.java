package ru.testpoint;

public class Distanse {

  public static void main(String[] args){

    Point p1 = new Point (2,5);
    Point p2 = new Point (2,9);
    Point.distance(p1,p2);

    System.out.println("Расстояние между точками = " + Point.distance(p1,p2));
  }


}
