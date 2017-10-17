package ru.job4j;

/**
 * Класс для опрделения цикличности связанного списка
 * @author smikhailov
 * @since 17.10.2017
 * @version 1
 */
public class FindCycle {

    /**
     * Метод на определение цикличности связанного списка
     * @param first первый элемент связанного спичка
     * @return true если список имеет цикличность
     */
    boolean hasCycle(Node first) {
        boolean result = false;

        Node slow, fast;

        slow = fast = first;

        while(true) {

            slow = slow.next;

            if(fast.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }

            if(slow == null || fast == null) {
                break;
            }

            if(slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * @param <T>
     */
    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}


