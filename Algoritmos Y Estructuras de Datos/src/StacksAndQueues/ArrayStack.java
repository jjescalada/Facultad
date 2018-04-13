package StacksAndQueues;

public class ArrayStack<T> implements Stack<T> {
    private T[] array;

    public ArrayStack(int size){
        array = (T[])(new Object[size]);
    }

    @Override
    public void push(T element) {
        if (isFull()) return;
        for (int i=0;i<array.length;i++) {
            if (array[i] == null) {
                array[i] = element;
                return;
            }
        }
    }

    @Override
    public T pop(){
        for(int i = array.length-1; i>-1; i--) {
            if (array[i] != null && array[i-1] != null) {
                T result = array[i];
                array[i] = null;
                return result;
            }
        }
        return null;
    }

    public T peek(){
        for(int i = array.length-1; i<-1; i--) {
            if (array[i]==null && array[i-1] != null) {
                return array[i-1];
            }
        }
        return null;
    }

    public boolean isFull() {
        return array[array.length - 1] != null;
    }

    @Override
    public boolean isEmpty() {
        return array[0] == null;
    }

    @Override
    public String toString(Stack<T> myStack) {
        String res = "";
        for (T elem: array) {
            if (elem!=null) {
                res += elem.toString() + ", ";
            } else {
                res += "null, ";
            }
        }
        res += "END";
        return res;
    }
}
