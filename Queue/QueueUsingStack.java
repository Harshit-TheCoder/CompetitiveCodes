package Queue;

import java.util.Stack;

public class QueueUsingStack {
    Stack < Integer > input = new Stack < > ();
    Stack < Integer > output = new Stack < > ();
    /** Initialize your data structure here. */
    public QueueUsingStack() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        System.out.println("The element pushed is " + x);
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // shift input to output 
        if (output.empty())
            while (input.empty() == false) {
                output.push(input.peek());
                input.pop();
            }


        int x = output.peek();
        output.pop();
        return x;
    }

    /** Get the front element. */
    public int peek() {
        // shift input to output 
        if (output.empty())
            while (input.empty() == false) {
                output.push(input.peek());
                input.pop();
            }
        return output.peek();
    }
    int size() {
        return (output.size() + input.size());
    }

}
