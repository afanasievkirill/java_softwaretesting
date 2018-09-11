package ru.testpoint;


import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanseTest {

  @Test
  public void testArea1(){
    Point p1 = new Point (2,5);
    Point p2 = new Point (2,9);
    Assert.assertEquals(p1.distance(p2), 4);

  }

  @Test
  public void testArea2(){
    Point p1 = new Point (-2,-5);
    Point p2 = new Point (-2,-9);
    Assert.assertEquals(p1.distance(p2), 4);

  }

  @Test
  public void testArea3(){
    Point p1 = new Point (-2,-5);
    Point p2 = new Point (6,7);
    Assert.assertEquals(p1.distance(p2), 14.422205101855956);

  }

  @Test
  public void testArea4(){
    Point p1 = new Point (0.1,-5);
    Point p2 = new Point (6,0.333);
    Assert.assertEquals(p1.distance(p2), 7.95304275104818);

  }

}
