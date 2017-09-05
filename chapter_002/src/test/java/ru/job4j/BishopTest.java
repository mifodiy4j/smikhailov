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
public class BishopTest {
	/**
	* Test bishop.
	*/
	@Test
	public void whenBishopGoAcrossTwoCell() {
      Cell cellStart = new Cell(2,3);
		Bishop bishop = new Bishop(cellStart);
		
      Cell cellDist = new Cell(5,6);

      Cell[] cellArrayResult = new Cell[] {new Cell(3,4), new Cell(4,5)};

		assertThat(bishop.way(cellDist)[0].getString(), is(cellArrayResult[0].getString()));
      assertThat(bishop.way(cellDist)[0].getColumn(), is(cellArrayResult[0].getColumn()));

      assertThat(bishop.way(cellDist)[1].getString(), is(cellArrayResult[1].getString()));
      assertThat(bishop.way(cellDist)[1].getColumn(), is(cellArrayResult[1].getColumn()));
	}

   @Test(expected = ImpossibleMoveException.class)
   public void whenBishopInvalidStep() {
      Cell cellStart = new Cell(2,3);
      Bishop bishop = new Bishop(cellStart);
      
      Cell cellDist = new Cell(5,5);

      bishop.way(cellDist);

   }

}