package tqsstack;

import java.util.NoSuchElementException;
import java.util.Stack;

public class App {

    public int maxSize;
    public Stack<Integer> stack = new Stack<Integer>();


    public App() {
        maxSize = -1;
    }

    public App(int maxSize) {
        this.maxSize = maxSize;
    }


    public void push(int x) {
        if(stack.size() == maxSize) {
            throw new IllegalStateException();
        } else {
            stack.push(x);
        }
    }

    public int pop() {
        try {
            return stack.pop();
        } catch(Exception e) {
            throw new NoSuchElementException("Popped empty stack");
        }
    }

    public int peek() {
        try {
            return stack.peek();
        } catch(Exception e) {
            throw new NoSuchElementException("Peeked empty stack");
        }
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
