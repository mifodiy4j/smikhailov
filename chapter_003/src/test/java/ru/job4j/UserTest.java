package ru.job4j;

import ru.job4j.*;

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
public class UserTest {
	/**
	* Test user.
	*/
   @Test
   public void whenListConvertToMap7Elements() {

       List<User> list = new ArrayList<>();
       User user1 = new User(456, "Anton", "Moscow");
       User user2 = new User(789, "Boris", "Anapa");
       User user3 = new User(321, "Victor", "Tver");

       list.add(user1);
       list.add(user2);
       list.add(user3);

       HashMap<Integer, User> result = User.process(list);

       HashMap<Integer, User> expectation = new HashMap<Integer, User>();
       expectation.put(user1.getId(), user1);
       expectation.put(user2.getId(), user2);
       expectation.put(user3.getId(), user3);

       assertThat(result, is(expectation));

   }
}