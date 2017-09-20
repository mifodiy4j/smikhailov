package ru.job4j;

import ru.job4j.models.*;
import java.util.*;

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
		Iterator<Item> iter = tracker.getAll().iterator();
	 	assertThat(iter.next(), is(item));
	}

	/**
	* Test update.
	*/
	@Test
	public void whenUpdateFirstItem() {
	 	Tracker tracker = new Tracker();

		Item item1 = new Item("test1","testDescription1",123L);
	 	tracker.add(item1);

		Item item2 = new Item("test2","testDescription2",321L);
	 	item2.setId(item1.getId());

		tracker.update(item2);

		Item itemFirst = tracker.getAll().get(0);

	 	assertThat(itemFirst.name, is(item2.name));
	 	assertThat(itemFirst.description, is(item2.description));
	 	assertThat(itemFirst.create, is(item2.create));
	}

	/**
	* Test delete.
	*/
	@Test
	public void whenDeleteSecondElementOfThree() {
	 	Tracker tracker = new Tracker();

		Item item1 = new Item("test1","testDescription1",123L);
	 	tracker.add(item1);

		Item item2 = new Item("test2","testDescription2",231L);
	 	tracker.add(item2);

		Item item3 = new Item("test3","testDescription3",321L);
	 	tracker.add(item3);

		tracker.delete(item2);

		ArrayList<Item> result = new ArrayList<>();
	 	result.add(item1);
	 	result.add(item3);

	 	assertThat(tracker.getAll(), is(result));
	}

	/**
	* Test FindAll.
	*/
	@Test
	public void whenFindAllCreateArrayOfFirstAndThirdElements() {
		 Tracker tracker = new Tracker();

		 Item item1 = new Item("test1","testDescription1",123L);
		 tracker.add(item1);

		 Item item2 = new Item("test2","testDescription2",231L);
		 tracker.add(item2);

		 Item item3 = new Item("test3","testDescription3",321L);
		 tracker.add(item3);

	 	tracker.delete(item2);

		ArrayList<Item> result = new ArrayList<>();
	 	result = tracker.findAll();

		ArrayList<Item> expected = new ArrayList<>();
		expected.add(item1);
		expected.add(item3);

	 	assertThat(result, is(expected));
	}

	/**
	* Test FindByName.
	*/
	@Test
	public void whenFindByNameThreeElementsOfFourElements() {
		Tracker tracker = new Tracker();

		Item item1 = new Item("test1","testDescription1",123L);
		tracker.add(item1);

		Item item2 = new Item("testFind","testDescription2",231L);
		tracker.add(item2);

		Item item3 = new Item("testFind","testDescription3",321L);
		tracker.add(item3);

		Item item4 = new Item("testFind","testDescription4",132L);
		tracker.add(item4);

		ArrayList<Item> result = new ArrayList<>();
		result = tracker.findByName("testFind");

		ArrayList<Item> expected = new ArrayList<>();
		expected.add(item2);
		expected.add(item3);
		expected.add(item4);

		assertThat(result, is(expected));
	}

	/**
	* Test FindById.
	*/
	@Test
	public void whenSearchIdIsMissingFromFourElements() {
		Tracker tracker = new Tracker();

		Item item1 = new Item("test1","testDescription1",123L);
		tracker.add(item1);

		Item item2 = new Item("test2","testDescription2",231L);
		tracker.add(item2);

		Item item3 = new Item("test3","testDescription3",321L);
		tracker.add(item3);

	 	Item item4 = new Item("test4","testDescription4",132L);
	 	tracker.add(item4);

		Item result = tracker.findById("1");

		Item expected = null;

	 	assertThat(result, is(expected));
	}

	/**
	* Test FindById.
	*/
	@Test
	public void whenSearchIdIsSecondFromFourElements() {
	 	Tracker tracker = new Tracker();

	 	Item item1 = new Item("test1","testDescription1",123L);
	 	tracker.add(item1);

	 	Item item2 = new Item("test2","testDescription2",231L);
	 	tracker.add(item2);

	 	Item item3 = new Item("test3","testDescription3",321L);
	 	tracker.add(item3);

	 	Item item4 = new Item("test4","testDescription4",132L);
	 	tracker.add(item4);

	 	Item result = tracker.findById(item2.getId());
	 	Item expected = item2;

	 	assertThat(result, is(expected));
	}

}