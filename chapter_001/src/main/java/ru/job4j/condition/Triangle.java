package ru.job4j.condition;

public class Triangle {
  private Point a;
  private Point b;
  private Point c;
 
  public Triangle(Point a, Point b, Point c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  double ab = a.distanceTo(b);
  double ac = a.distanceTo(c);
  double bc = b.distanceTo(c);

  public double area() {

    if ((c.getX() - b.getX()) * (b.getX() - a.getX()) == (c.getY() - b.getY()) * (b.getY() - a.getY())) {
      if ((c.getX() - a.getX()) * (c.getX() - b.getX()) == (c.getY() - a.getY()) * (c.getY() - b.getY())) {
        return -1;
      }
    }

      //calculate the triangle area  
    return Math.abs(a.getX() * (b.getY() - c.getY()) + b.getX() * (c.getY() - a.getY()) + c.getX() * (a.getY() - b.getY()) / 2);
  }
}