package ru.job4j;

import ru.job4j.models.*;

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
public class StubInputTest {
	/**
	* Test createItem.
	*/
	@Test
	public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
   		Tracker tracker = new Tracker();     // создаём Tracker
   		Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
   		new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
   		assertThat(tracker.getAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
	}
	
	/**
	* Test edit.
	*/
	@Test
 	public void whenUpdateThenTrackerHasUpdatedValue() {
	    // создаём Tracker
	    Tracker tracker = new Tracker();
	    //Напрямую добавляем заявку
	    Item item = tracker.add(new Item());
	    //создаём StubInput с последовательностью действий
	    Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
	    // создаём StartUI и вызываем метод init()
	    new StartUI(input, tracker).init();
	    // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
	    assertThat(tracker.findById(item.getId()).getName(), is("test name"));
	}


}