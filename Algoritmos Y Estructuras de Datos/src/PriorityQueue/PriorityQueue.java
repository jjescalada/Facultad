package PriorityQueue;

public class PriorityQueue<T> {
    Node<T> array[]; //empieza en 1
    //NOTA: El "heap" es algo CONCEPTUAL, lo que vendria a ser el heap lo convertimos en un array, para poder bajar la complejidad de ciertas operaciones
    //el acceso a un cierto elemento usando arboles binarios tendria una complejidad de O(log n), al igual que acceder al elemento minimo
    //usando un array, la complejidad de acceso a un elemento arbitrario se reduce a O(1), y acceder al elemento minimo tambien se reduce a O(1)
    int firstEmptyIndex;

    public PriorityQueue(int size) {
        array = new Node[size];
        firstEmptyIndex = 0;
    }

    public PriorityQueue(){
        new PriorityQueue(11);
    }

    public void enqueue(T elem, double priority){
        if (array[array.length-1] != null) resize();
        System.out.println("first empty index is: "+firstEmptyIndex);
        array[firstEmptyIndex] = new Node(elem,priority);
        floatMe(firstEmptyIndex);
        firstEmptyIndex++;
        System.out.println("first empty index is now: "+firstEmptyIndex);
    }

    public T dequeue() {
        //todo se fija que no este vacio
        T result = array[0].element;
        array[0] = array[firstEmptyIndex-1];
        array[firstEmptyIndex-1] = null;
        firstEmptyIndex--;
        sinkMe();
        return result;
    }

    private void floatMe(int position) {
        for (int i = position; i>=0; i = (i-1)/2) {
            System.out.println("item "+array[i].priority+" in position "+i+" will be floated!");
            if (array[i].priority < array[(i-1) / 2].priority) {
                swap(i,(i-1)/2);
            } else {
                System.out.println("broken!");
                break;
            }
        }
    }

    private void resize(){
        Node<T> newArray[] = new Node[array.length*2];
        int i = 0;
        for (Node node: array) {
            newArray[i] = new Node(node.element,node.priority);
            i++;
        }
        array = newArray;
    }

    private void sinkMe() {
        for (int i = 0; i<firstEmptyIndex;) {
            System.out.println("i will be sunk! i = "+i);
            int leftIndex = (i*2)+1;
            int rightIndex = (i*2)+2;
            if (leftIndex >= firstEmptyIndex || rightIndex >= firstEmptyIndex) return;
            if (array[i].priority < array[rightIndex].priority && array[i].priority < array[leftIndex].priority) return;
            boolean leftIsGreater = (array[leftIndex].priority-array[rightIndex].priority)>=0;

            if (leftIsGreater && array[i].priority > array[rightIndex].priority) {
                swap(i,rightIndex);
                i = rightIndex;
            } else if (!leftIsGreater && array[i].priority > array[leftIndex].priority) {
                swap(i,leftIndex);
                i = leftIndex;
            }

        }
    }

    private void swap(int positionA, int positionB) {
        Node temp = array[positionA];
        array[positionA] = array[positionB];
        array[positionB] = temp;
        System.out.println("swapped "+array[positionA].priority+" and "+array[positionB].priority);
    }

    public void printMe() {
        System.out.print("[");
        for (Node node: array) {
            if (node == null) {
                System.out.print("]");
                return;
            }
            System.out.print(node.priority+", ");
        }
        System.out.println("]");
    }
}
