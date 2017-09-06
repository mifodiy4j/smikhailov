package ru.job4j;

import ru.job4j.*;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Sergey Mikhailov (mailto:mifodiy67@mail.ru)
* @version $Id$
* @since 0.1
*/
public class BoardTest {
	/**
	* Test board.
	*/
   @Test(expected = FigureNotFoundException.class)
   public void whenFigureNotFound() {
      
      Figure[] figures = new Figure[] {
         new Bishop(new Cell(0, 2)),
         new Bishop(new Cell(0, 5))
      };

      Board board = new Board(figures);

      boolean b = board.move(new Cell(0,0), new Cell(1,1));

   }

}