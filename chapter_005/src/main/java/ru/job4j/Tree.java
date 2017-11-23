package ru.job4j;

import java.util.*;

class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Stack<Node<E>> stack = new Stack<>();
    Node<E> root;
    List<E> listTree = new ArrayList<>();

    /**
     * Класс ячейка
     * @param <E>
     */
    class Node<E> {
        List<Node<E>> children = new ArrayList<>();
        private E value;

        /**
         * Конструктор для ячейки дерева
         * @param value
         */
        public Node(E value) {
            this.value = value;
        }

        public List<Node<E>> getChildren() {
            return children;
        }
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
            root = new Node<>(parent);
        }
        stack.push(root);
        while (!stack.isEmpty()) {

            Node<E> element = stack.pop();
            if (compare(element, parent) == 0) {
                if (!containsInCollection(child)) {
                    Node<E> chi = new Node<>(child);
                    element.children.add(chi);
                    return true;
                } else {
                    return false;
                }
            }
            List<Node<E>> childrens = element.getChildren();
            for (int i = 0; i < childrens.size(); i++) {
                Node<E> n = childrens.get(i);
                if (n != null) {
                    stack.push(n);
                }
            }
        }
        return false;
    }

    /**
     * Проверка наличия элемента в коллекции по значению ячейки
     * @param e
     * @return false - нет элемента в коллекции, true - есть
     */
    public boolean containsInCollection(E e) {
        Iterator<E> iter = iterator();
        while(iter.hasNext()) {
            if (iter.next().equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Сравнение значения ячейки с другим значением
     * @param first
     * @param second
     * @return 0 - если равны, >0 - если значение ячейки больше проверямого значения, <0 - в других случаях
     */
    private int compare(Node<E> first, E second) {
        E e = first.value;
        return e.compareTo(second);
    }

    /**
     * Итератор
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        //bfs(root);
        dfs(root);
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (index >= listTree.size()) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                return listTree.get(index++);
            }
        };
    }

    /**
     * метод добавления значения ячеик в listTree (без дубликатов)
     * основан на методе поиска в глубину
     * @param node
     */
    public void dfs(Node<E> node) {
        Stack<Node<E>> stackDfs = new Stack<>();
        stackDfs.push(node);
        while (!stackDfs.isEmpty()) {
            boolean markerInList = false;
            Node<E> element = stackDfs.pop();
            for (E e : listTree) {
                if (e.equals(element.value)) {
                    markerInList = true;
                }
            }
            if (!markerInList) {
                listTree.add(element.value);
            }

            List<Node<E>> childrens = element.getChildren();
            for (int i = 0; i < childrens.size(); i++) {
                Node<E> n = childrens.get(i);
                if(n != null) {
                    stackDfs.push(n);
                }
            }
        }
    }

    /**
     * Проверка относится дерево к бинарному
     * @return true - бинарное, иначе нет
     */
    public boolean isBinary() {
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> element = stack.pop();

            List<Node<E>> childrens = element.getChildren();
            if (childrens.size() > 2) {
                return false;
            }
            for (int i = 0; i < childrens.size(); i++) {
                Node<E> n = childrens.get(i);
                if(n != null) {
                    stack.push(n);
                }
            }
        }
        return true;
    }

}