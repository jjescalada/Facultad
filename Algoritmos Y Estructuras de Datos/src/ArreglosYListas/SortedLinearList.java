package ArreglosYListas;

import java.util.Comparator;

public class SortedLinearList<T> {
    private Node<T> first;
    private Comparator<T> cmp;

    public SortedLinearList(Comparator<T> cmp) {
        this.cmp = cmp;
    }

    public boolean isEmpty(){
        return first==null;
    }

    public boolean contains(T elem) {
        return contains(first,elem);
    }

    private boolean contains(Node<T> node,T elem) { //podria ser static T no fuera comparable (?)
        if (elem == null) return false;
        int c = cmp.compare(node.head,elem);
        if (c == 0) return true;
        if (c>0) return false; //es una lista ordenada
        else return contains(node.tail,elem);
    }

    public void add(T elem) {
        first = add(elem,first);
    }

    private Node<T> add(T elem, Node<T> node) {
        if (cmp.compare(node.head,elem) > 0 || node == null) return new Node(elem,node);
        node.tail = add(elem,node.tail);
        return node;
    }

    public void remove(T elem) {
        first = remove(elem,first);
    }

    private Node<T> remove(T elem, Node<T> node) {
        if (node == null) return node;
        int c = cmp.compare(node.head,elem);
        if (c==0) return first.tail;
        return this.first; //todo fix
    }


    private static class Node<T> {
        T head;
        Node<T> tail;

        Node(T head, Node<T> tail){
            this.head = head;
            this.tail = tail;
        }
    }
}
