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
 
  public double area() {
    //calculate the triangle area  
    return Math.abs(a.getX() * (b.getY() - c.getY()) + b.getX() * (c.getY() - a.getY()) + c.getX() * (a.getY() - b.getY()) / 2);
  }
}