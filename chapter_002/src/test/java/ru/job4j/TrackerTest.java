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
public class TrackerTest {
	/**
	* Test add.
	*/
	@Test
	public void whenAddNewItemThenTrackerHasSameItem() {
	 Tracker tracker = new Tracker();
	 Item item = new Item("test1","testDescription",123L);
	 tracker.add(item);
	 assertThat(tracker.getAll()[0], is(item));
	}

	/**
	* Test update.
	*/
	@Test
	public void whenUpdate() {
	 Tracker tracker = new Tracker();
	 Item item1 = new Item("test1","testDescription1",123L);
	 tracker.add(item1);
	 //Item item2 = new Item("test2","testDescription2",321L);
	 //item2.setId(item1.getId());
	 //tracker.update(item2);
	 assertThat(tracker.getAll()[0], is(item1));
	}

	/**
	* Test delete.
	*/
	@Test
	public void whenDelete() {
	 Tracker tracker = new Tracker();
	 Item item1 = new Item("test1","testDescription1",123L);
	 tracker.add(item1);
	 Item item2 = new Item("test2","testDescription2",321L);
	 tracker.add(item2);
	 tracker.delete(item2);
	 Item[] result = new Item[2];
	 result[0] = item1;
	 result[1] = item2;
	 assertThat(tracker.getAll(), is(result));
	}

	/**
	* Test FindAll.
	*/
	@Test
	public void whenFindAllCreateArraOfFirstAndThirdElements() {
	 Tracker tracker = new Tracker();
	 Item item1 = new Item("test1","testDescription1",123L);
	 tracker.add(item1);
	 Item item2 = new Item("test2","testDescription2",231L);
	 tracker.add(item2);
	 Item item3 = new Item("test3","testDescription3",321L);
	 tracker.add(item3);
	 tracker.delete(item2);
	 Item[] result = tracker.findAll();
	 Item[] expected = new Item[2];
	 expected[0] = item1;
	 expected[1] = item3;
	 assertThat(result, is(expected));
	}

}