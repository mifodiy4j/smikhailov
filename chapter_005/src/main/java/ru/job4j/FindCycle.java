package ru.job4j;

/**
 * Created by SERG on 16.10.2017.
 */
public class FindCycle {

    boolean hasCycle(Node first) {
        if(first == null)
            return false;

        Node slow, fast;

        slow = fast = first;

        while(true) {

            slow = slow.next;

            if(fast.next != null)
                fast = fast.next.next;
            else
                return false;

            if(slow == null || fast == null)
                return false;

            if(slow == fast)
                return true;
        }
    }

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}


