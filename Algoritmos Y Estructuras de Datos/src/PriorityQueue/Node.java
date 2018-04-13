package PriorityQueue;

public class Node<T> {
    T element;
    double priority;

    public Node (T elem, double priority) {
        this.element = elem;
        this.priority = priority;
    }
}
