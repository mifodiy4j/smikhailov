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
public class KnightTest {
	/**
	* Test bishop.
	*/
	@Test
	public void whenKnightFrom43GoTo55() {
      Cell cellStart = new Cell(4,3);
		Knight knight = new Knight(cellStart);
		
      Cell cellDist = new Cell(5,5);

      Cell[] cellArrayResult = new Cell[] {new Cell(4,3), new Cell(5,3), new Cell(4,4), new Cell(5,4), new Cell(4,5), new Cell(5,5)};

		assertThat(knight.way(cellDist)[0].getString(), is(cellArrayResult[0].getString()));
      assertThat(knight.way(cellDist)[0].getColumn(), is(cellArrayResult[0].getColumn()));

      assertThat(knight.way(cellDist)[1].getString(), is(cellArrayResult[1].getString()));
      assertThat(knight.way(cellDist)[1].getColumn(), is(cellArrayResult[1].getColumn()));

      assertThat(knight.way(cellDist)[2].getString(), is(cellArrayResult[2].getString()));
      assertThat(knight.way(cellDist)[2].getColumn(), is(cellArrayResult[2].getColumn()));

      assertThat(knight.way(cellDist)[3].getString(), is(cellArrayResult[3].getString()));
      assertThat(knight.way(cellDist)[3].getColumn(), is(cellArrayResult[3].getColumn()));

      assertThat(knight.way(cellDist)[4].getString(), is(cellArrayResult[4].getString()));
      assertThat(knight.way(cellDist)[4].getColumn(), is(cellArrayResult[4].getColumn()));

      assertThat(knight.way(cellDist)[5].getString(), is(cellArrayResult[5].getString()));
      assertThat(knight.way(cellDist)[5].getColumn(), is(cellArrayResult[5].getColumn()));
	}

   @Test(expected = ImpossibleMoveException.class)
   public void whenKnightpInvalidStep() {
      Cell cellStart = new Cell(4,3);
      Knight knight = new Knight(cellStart);
      
      Cell cellDist = new Cell(3,2);

      knight.way(cellDist);

   }

}