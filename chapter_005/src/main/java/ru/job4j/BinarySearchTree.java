package ru.job4j;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<E>> implements SimpleBinarySearchTree<E> {

    private Node<E> nodeRoot;

    class Node<E extends Comparable<E>> implements Comparable<Node<E>> {

        E value;

        Node<E> nodeLeft;

        Node<E> nodeRight;

        public Node(E value) {
            this.value = value;
            this.nodeLeft = null;
            this.nodeRight = null;
        }

        @Override
        public int compareTo(Node<E> o) {
            return this.value.compareTo(o.value);
        }
    }

    @Override
    public boolean add(E value) {
        boolean result = false;
        if (this.nodeRoot == null) {
            this.nodeRoot = new Node<>(value);
            result = true;
        } else {
            result = addToTree(this.nodeRoot, new Node<>(value));
        }
        return result;
    }

    private boolean addToTree(Node<E> nodeRoot, Node<E> node) {
        boolean result = false;
        if (nodeRoot.compareTo(node) >= 0) {
            if (nodeRoot.nodeLeft != null) {
                result = this.addToTree(nodeRoot.nodeLeft, node);
            } else {
                nodeRoot.nodeLeft = node;
                result = true;
            }
        } else {
            if (nodeRoot.nodeRight != null) {
                result = this.addToTree(nodeRoot.nodeRight, node);
            } else {
                nodeRoot.nodeRight = node;
                result = true;
            }
        }
        return result;
    }

    private void addToList(Node<E> root, List<E> list) {
        if (root != null) {
            addToList(root.nodeLeft, list);
            list.add(root.value);
            addToList(root.nodeRight, list);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Iterator<E> iterator;

            private List<E> list;

            @Override
            public boolean hasNext() {
                if (list == null) {
                    list = new LinkedList<>();
                    addToList(nodeRoot, list);
                    iterator = list.iterator();
                }
                return iterator.hasNext();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }
}
