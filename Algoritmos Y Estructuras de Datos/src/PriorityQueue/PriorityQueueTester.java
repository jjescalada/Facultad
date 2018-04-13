package PriorityQueue;
public class PriorityQueueTester {
    public static void main(String[] args) {
        PriorityQueue<String> myPriorityQueue = new PriorityQueue<String>(8);
        myPriorityQueue.enqueue("string A",2);
        myPriorityQueue.enqueue("string B",8);
        myPriorityQueue.enqueue("string C",12);
        myPriorityQueue.enqueue("string D",6);
        myPriorityQueue.enqueue("string E",4);
        myPriorityQueue.enqueue("String F",20);
        myPriorityQueue.enqueue("String G",1);
        myPriorityQueue.enqueue("String H",3);
        myPriorityQueue.enqueue("String I",7);
        myPriorityQueue.enqueue("String J",0);
        myPriorityQueue.printMe();
        System.out.println(myPriorityQueue.array.length);
        String deq1 = myPriorityQueue.dequeue();
        System.out.println(deq1);
        myPriorityQueue.printMe();
        String deq2 = myPriorityQueue.dequeue();
        String deq3 = myPriorityQueue.dequeue();
        String deq4 = myPriorityQueue.dequeue();
        System.out.println(deq2);
        System.out.println(deq3);
        System.out.println(deq4);
        myPriorityQueue.printMe();
        myPriorityQueue.enqueue("String K",-1);
        myPriorityQueue.enqueue("String L",2);
        myPriorityQueue.enqueue("String M",33);
        myPriorityQueue.enqueue("String N",10);
        myPriorityQueue.printMe();

    }
}
