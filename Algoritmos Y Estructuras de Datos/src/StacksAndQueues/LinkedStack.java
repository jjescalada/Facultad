package StacksAndQueues;

public class LinkedStack<T> implements Stack<T> {
    private T head;
    private LinkedStack<T> tail;

    public LinkedStack(){
        this(null,null);
    }

    private LinkedStack(T head,LinkedStack<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public void push(T element) {
        tail = new LinkedStack<>(head,tail);
        head = element;
    }

    @Override
    public T pop() {
        T result = head;
        head = tail.head;
        tail = tail.tail;
        return result;
    }

    @Override
    public T peek() {
        return head;
    }

    @Override
    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    @Override
    public String toString(Stack<T> myStack) {
        if (!myStack.isEmpty()) {
            return ((LinkedStack<T>)myStack).head+", "+toString(((LinkedStack<T>)myStack).tail);
        }
        return "END";
    }
}