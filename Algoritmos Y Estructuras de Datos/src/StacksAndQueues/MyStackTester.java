package StacksAndQueues;

public class MyStackTester {
    public static void main(String[] args) {
        Stack<Integer> myIntStack = new LinkedStack<>();
        myIntStack.push(5);
        myIntStack.push(10);
        myIntStack.push(20);
        myIntStack.push(25);
        System.out.println(myIntStack.toString(myIntStack));
        myIntStack.pop();
        System.out.println(myIntStack.toString(myIntStack));
        System.out.println(myIntStack.peek());

        Stack<Integer> myIntStack2 = new ArrayStack<>(5);
        myIntStack2.push(4);
        myIntStack2.push(8);
        myIntStack2.push(12);
        myIntStack2.push(16);
        System.out.println(myIntStack2.toString(myIntStack2));
        myIntStack2.pop();
        System.out.println(myIntStack2.toString(myIntStack2));
    }
}
