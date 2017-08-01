package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
* Test.
*
* @author Sergey Mikhailov (mailto:mifodiy67@mail.ru)
* @version $Id$
* @since 0.1
*/
public class TriangleTest {
    /**
    * Test area.
    */
	@Test
 	public void whenAreaSetThreePointsThenTriangleArea() {
	    // создаем три объекта класса Point.
	    Point a = new Point(0, 0);
	    Point b = new Point(0, 2);
	    Point c = new Point(2, 0);
	    // Создаем объект треугольник и передаем в него объекты точек.
	    Triangle triangle = new Triangle(a, b, c);
	    // Вычисляем площадь.
	    double result = triangle.area();
	    // Задаем ожидаемый результат.
	    double expected = 2D;
	    double ab = a.distanceTo(b);
  double ac = a.distanceTo(c);
  double bc = b.distanceTo(c);
	    //Проверяем результат и ожидаемое значение.
	    assertThat(result, closeTo(expected, 0.1));
 	}

 	/**
    * Test area.
    */
	@Test
 	public void whenThreePointsAreOneLines() {
	    // создаем три объекта класса Point.
	    Point a = new Point(0, 0);
	    Point b = new Point(1, 1);
	    Point c = new Point(2, 2);
	    // Создаем объект треугольник и передаем в него объекты точек.
	    Triangle triangle1 = new Triangle(a, b, c);
	    // Вычисляем площадь.
	    double result1 = triangle1.area();
	    // Задаем ожидаемый результат.
	    double expected1 = -1;
	    //Проверяем результат и ожидаемое значение.
	    assertThat(result1, is(expected1));
 	}
}