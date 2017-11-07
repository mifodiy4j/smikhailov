package ru.job4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    Node<E> root;

    class Node<E> {
        List<Node<E>> children;
        E value;

        public List<Node<E>> getChildren() {
            return children;
        }
    }

    @Override
    public boolean add(E parent, E child) {
        if (root == null) {
            root.value = parent;
            return true;
        } else {

            Iterator<E> iter = iterator();

            if (compare(node, parent) == 0) {
                node.children.add(child);
                return true;
            }
            return false;
        }
    }

    /**
     * Добавление элементов дерева в list
     * @param list список куда добавляем элементы
     * @param node элемент дерева, который необходимо добавить в список,
     *             и при наличие дочерних элементов также добавить.
     */
    public void addChild(List<E> list, Node<E> node) {
        list.add(node.value);
        for (Node<E> ch : node.children) {
            list.add(ch.value);
            if (ch.children != null) {
                addChild(list, ch);
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        List<E> listTree = new ArrayList<>();
        addChild(listTree, root);

        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                boolean res = false;
                if (index < listTree.size()) {
                    res = true;
                } else {
                    throw new NoSuchElementException();
                }
                return res;
            }

            @Override
            public E next() {
                return listTree.get(index++);
            }
        };
    }
}