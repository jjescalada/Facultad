package BSTiterators;

import java.util.Iterator;

public class PreorderIterator implements Iterator {

    private BinarySearchTree myTree;

    public PreorderIterator(BinarySearchTree bst) {
        myTree = bst;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
