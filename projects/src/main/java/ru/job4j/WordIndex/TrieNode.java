package ru.job4j.WordIndex;

import java.util.*;

/**
 * Класс реализующий узел Trie(префиксного дерева)
 */
public class TrieNode {

    private char data;
    private boolean isEnd;
    private int count;
    private LinkedList<TrieNode> childList;
    private Set<Integer> positionSet;

    /**
     * Constructor
     * @param c - символ
     */
    public TrieNode(char c)
    {
        childList = new LinkedList<TrieNode>();
        isEnd = false;
        data = c;
        count = 0;
        positionSet = new HashSet<>();
    }

    public LinkedList<TrieNode> getChildList() {
        return childList;
    }

    /**
     * Метод возвращает объект <>TrieNode</> если узел дерева Trie
     * имеет потомка с значением поля <>data = c</>,
     * в противном случае null
     * @param c символ
     * @return потомок узла, объект <>TrieNode</> с значением
     */
    public TrieNode getChild(char c)
    {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.getData() == c)
                    return eachChild;
        return null;
    }

    public char getData() {
        return data;
    }

    public void addPosition(int position) {
        positionSet.add(position);
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean getEnd() {
        return isEnd;
    }

    public Set<Integer> getPositionSet() {
        return positionSet;
    }

    public void incrementCount() {
        count++;
    }
}
