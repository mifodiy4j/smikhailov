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
      Board board = new Board();
      
      board.figures = {
         new Bishop(new Cell(0, 2))
      };

      board.move(new Cell(0,0), new Cell(1,1));

   }

}