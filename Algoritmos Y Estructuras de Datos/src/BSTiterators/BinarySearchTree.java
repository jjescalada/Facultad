package BSTiterators;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {

    private DoubleNode raiz;
    public BinarySearchTree(){
            raiz = null;
        }
        // METODOS COMUNES CON ARBOL BINARIO
        // precondicion: -
        public boolean esVacio(){
            return (raiz == null);
        }
        // precondición: árbol distino de vacío
        public T getRaiz(){ return raiz.elem;
        }
        // precondición: árbol distino de vacío
        public BinarySearchTree leftTree(){
            BinarySearchTree t = new BinarySearchTree();
            t.raiz = raiz.left;
            return t;
        }
        // precondición: árbol distino de vacío
        public BinarySearchTree rightTree(){
            BinarySearchTree t = new BinarySearchTree();
            t.raiz = raiz.right;
            return t;
        }
        // precondicion: -
        public boolean existe(T elem){
            return existe(raiz, elem);
        }
        // precondicion: árbol distinto de vacío
        public T getMin(){
            return getMin(raiz).elem;
        }
        // precondicion: árbol distinto de vacío
        public T getMax(){ return getMax(raiz).elem; }
        // precondicion: elemento a buscar pertenece al arbol
        public T buscar(T elem){
            return buscar(raiz, elem).elem;
        }
        // precondicion: elemento a insertar no pertenece al árbol
        public void insertar(T elem){
            raiz = insertar(raiz, elem);
        }
        // precondicion: elemento a eliminar pertenece al árbol
        public void eliminar(T elem){
            raiz = eliminar(raiz, elem);
        }
        // METODOS PRIVADOS
        private boolean existe(DoubleNode t, T elem) {
            if (t == null) return false;
            if (elem.compareTo(t.elem) == 0) return true;
            else if (elem.compareTo( t.elem)< 0)
                return existe(t.left, elem);
            else
                return existe(t.right, elem);
        }
        private DoubleNode getMin(DoubleNode t){
            if (t.left == null) return t;
            else return getMin(t.left);
        }
        private DoubleNode getMax(DoubleNode t){
            if (t.right == null) return t;
            else return getMax(t.right);
        }
        private DoubleNode buscar(DoubleNode t, T elem){
            if (elem.compareTo( t.elem)== 0) return t;
            else if (elem.compareTo( t.elem)< 0)
                return buscar(t.left, elem);
            else
                return buscar(t.right, elem);
        }
        private DoubleNode insertar (DoubleNode t, T elem) {
            if (t == null){ t = new DoubleNode();
            t.elem = elem;
            }
            else if (elem.compareTo(t.elem) < 0)
                t.left = insertar(t.left, elem);
            else t.right = insertar(t.right, elem);
            return t;
        }
        private DoubleNode eliminar (DoubleNode t, T elem) {
            if (elem.compareTo(t.elem) < 0)
                t.left = eliminar(t.left, elem);
            else if (elem.compareTo(t.elem) > 0)
                t.right = eliminar(t.right, elem);
            else if (t.left != null && t.right != null) {
                t.elem = getMin(t.right).elem;
                t.right = eliminarMin(t.right);
            } else if (t.left != null)
                t = t.left;
            else t =t.right;
            return t;
        }
        private DoubleNode eliminarMin(DoubleNode t){
            if (t.left != null)
                t.left = eliminarMin(t.left);
            else
                t = t.right;
            return t;
        }

    @Override //default, ordena usando preorder
    public Iterator iterator() {
        return new PreorderIterator(this);
    }

    public Iterator inorderIterator(){

    }

    public Iterator postorderIterator(){

    }
}