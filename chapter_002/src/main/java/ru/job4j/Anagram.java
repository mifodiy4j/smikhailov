package ru.job4j;

import java.util.*;

public class Anagram{
	private String word1, word2;
	
	public Anagram(String word1, String word2) {
		this.word1 = word1;
		this.word2 = word2;
	}

	public String checkAnagram() {
		if (sort(word1).equals(sort(word2))) {
			return "YES";
		} else {
			return "NO";
		}
	}

	static String sort(String string) {
		char[] content = string.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
}