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
   		Item item = tracker.add(new Item());
   		Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "a", "b", "2", item.getId(), "q", "w", "6"});   //создаём StubInput с последовательностью действий
   		new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
   		assertThat(tracker.getAll()[1].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
   		assertThat(tracker.getAll()[2].getName(), is("a"));
   		assertThat(tracker.findById(item.getId()).getName(), is("q"));
	}

	/**
	* Test createItem.
	*/
	@Test
	public void whenUserAddItemThenTrackerHasNewItemWithSameName1() {
   		Tracker tracker = new Tracker();     // создаём Tracker
   		Item item = tracker.add(new Item());
   		Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "a", "b", "2", item.getId(), "q", "w", "6"});   //создаём StubInput с последовательностью действий
   		new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
   		assertThat(tracker.getAll()[1].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
   		assertThat(tracker.getAll()[2].getName(), is("a"));
   		assertThat(tracker.findById(item.getId()).getName(), is("q"));
	}


}