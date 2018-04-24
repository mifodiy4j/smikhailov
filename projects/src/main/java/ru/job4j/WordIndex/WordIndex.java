package ru.job4j.WordIndex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Класс для нахождения позиции слова в файле
 */
public class WordIndex {

    private static final Logger Log = LoggerFactory.getLogger(WordIndex.class);

    private TrieNode root;

    /**
     * Конструктор
     */
    public WordIndex() {
        root = new TrieNode(' ');
    }

    /**
     * Загрузка данных из файла и построение индекса
     * @param filename имя файла
     */
    public void loadFile(String filename) {
        FileReader fr = null;
        try {
            fr = new FileReader(filename);
            Scanner scan = new Scanner(fr);

            int i = 1;
            while (scan.hasNextLine()) {
                String str = scan.nextLine();

                StringTokenizer stk = new StringTokenizer(str, " ");
                while (stk.hasMoreElements()) {
                    String word = stk.nextToken();
                    insert(word, i);
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            Log.error(e.getMessage(), e);
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        } finally {
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Возвращает список позиций слова в файле.
     * Если данного слова в файле нет, то возвращает null
     * @param searchWord искомое слово
     * @return спиок <>Set</> позиций или <>null</> если нет вхождений
     */
    public Set<Integer> getIndexes4Word(String searchWord) {

        TrieNode current = root;
        for (char ch : searchWord.toCharArray() )
        {
            if (current.getChild(ch) == null)
                return null;
            else
                current = current.getChild(ch);
        }
        if (current.getEnd())
            return current.getPositionSet();
        return null;
    }

    /**
     * Добавляет слово и его позицию в тексте
     * в Trie(префиксное дерево),
     * создавая структуру дерева из букв слова.
     * @param word слово для вставки
     * @param position позиция слова в тексте
     */
    public void insert(String word, int position)
    {
        TrieNode current = root;
        for (char ch : word.toCharArray() ) {
            TrieNode child = current.getChild(ch);
            if (child != null)
                current = child;
            else {
                current.getChildList().add(new TrieNode(ch));
                current = current.getChild(ch);
            }
            current.incrementCount();
        }
        current.setEnd(true);
        current.addPosition(position);
    }
}
