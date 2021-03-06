package ru.job4j;

import java.util.HashMap;
import java.util.Map;

public class Anagramma {
    private String name1;
    private String name2;

    Map<Character, Integer> letters = new HashMap<>();

    public Anagramma(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public boolean wordsIsAnagramm() {

        if (name1.length() != name2.length()) {
            return false;
        }

        int k = 0;
        for (int i = 0; i < name1.length(); i++) {
            if (letters.putIfAbsent(name1.charAt(i), 1) != null) {
                k = letters.get(name1.charAt(i));
                letters.put(name1.charAt(i), ++k);
            }
        }

        for (int j = 0; j < name2.length(); j++) {
            if (letters.containsKey(name2.charAt(j))) {
                k = letters.remove(name2.charAt(j));
                if (k > 1) {
                    letters.put(name2.charAt(j), --k);
                }
            }
        }

        return letters.isEmpty();
    }
}
