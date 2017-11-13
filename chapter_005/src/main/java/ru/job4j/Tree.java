package ru.job4j;

import java.util.*;

class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    Node<E> root;
    List<E> listTree = new ArrayList<>();

    class Node<E> {
        List<Node<E>> children = new ArrayList<>();
        private E value;
    }

    /**
     * Добавление элемента дерева, по значению родительского узла
     * @param parent - значение родительского узла
     * @param child
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        if (root == null) {
            root = new Node<>();
            root.value = parent;
            addChild(listTree, root);
        }
        Iterator<E> iter = listTree.iterator();

        while(iter.hasNext()) {
            E element = iter.next();
            Node<E> node = new Node<>();
            node.value = element;
            if (compare(node, parent) == 0) {
                Node<E> chi = new Node<>();
                chi.value = child;
                node.children.add(chi);
                addChild(listTree, node);
                return true;
            }
        }
        return false;

    }

    /**
     * @param first
     * @param second
     * @return
     */
    private int compare(Node<E> first, E second) {
        E e = first.value;
        return e.compareTo(second);
    }

    /**
     * Добавление элементов дерева в list, добавление значения Node и
     * при наличие дочерних элементов - добавить дочерние элементы
     * @param list список куда добавляем элементы
     * @param node элемент дерева, который необходимо добавить в список,
     *             и при наличие дочерних элементов также добавить.
     */
    public void addChild(List<E> list, Node<E> node) {
        boolean elementInList = false;
        for (E elementList : list) {
            if (elementList.equals(node.value)) {
                elementInList = true;
            }
        }
        if (!elementInList) {
            list.add(node.value);
        }
        if (node.children != null) {
            for (Node<E> ch : node.children) {
                addChild(list, ch);
            }
        }
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (index >= listTree.size()) {
                    throw new NoSuchElementException();
                }
                return true;
            }

            @Override
            public E next() {
                return listTree.get(index++);
            }
        };
    }
}