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
public class AnagramTest {
	/**
	* Test anagram.
	*/
   @Test
   public void whenWordIsNotAnagram() {
      
      Anagram anagram = new Anagram("dabc", "abcb");
      
      assertThat(anagram.checkAnagram(), is("NO"));

   }

   @Test
   public void whenWordIsAnagram() {
      
      Anagram anagram = new Anagram("dabc", "adcb");
      
      assertThat(anagram.checkAnagram(), is("YES"));

   }

}