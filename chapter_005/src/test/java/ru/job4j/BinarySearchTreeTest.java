package ru.job4j;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void whenAddSortedItems() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(1);
        binarySearchTree.add(2);
        binarySearchTree.add(3);
        binarySearchTree.add(4);
        binarySearchTree.add(5);
        binarySearchTree.add(6);
        binarySearchTree.add(7);

        Iterator<Integer> iterator = binarySearchTree.iterator();
        int[] result = new int[]{
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next()
        };
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7};

        assertThat(result, is(expected));
    }

    @Test
    public void whenAddItemsInRandomOrder() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(77);
        binarySearchTree.add(2);
        binarySearchTree.add(8);
        binarySearchTree.add(3);
        binarySearchTree.add(44);
        binarySearchTree.add(11);
        binarySearchTree.add(5);

        Iterator<Integer> iterator = binarySearchTree.iterator();
        int[] result = new int[]{
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next()
        };
        int[] expected = new int[]{2, 3, 5, 8, 11, 44, 77};

        assertThat(result, is(expected));
    }

}