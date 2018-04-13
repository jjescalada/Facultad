package BSTiterators;

public class DoubleNode<T extends Comparable<? super T>> {
    DoubleNode left;
    DoubleNode right;
    T elem;

    public DoubleNode(T elem){
        this.elem = elem;
    }

    public DoubleNode() {
        this.elem = null;
    }


}
