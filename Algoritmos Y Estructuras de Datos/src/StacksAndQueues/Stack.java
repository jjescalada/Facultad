package StacksAndQueues;

public interface Stack<T> {
    void push(T element);
    T pop();
    T peek();
    boolean isEmpty();
    String toString(Stack<T> myStack);
}