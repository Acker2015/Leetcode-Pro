package lt_200_299;


import java.util.Stack;

/**
 * [232] Implement Queue using Stacks
 *
 * https://leetcode.com/articles/implement-queue-using-stacks/
 */
public class LC_232 {
    Stack<Integer> s1;
    Stack<Integer> s2;
    private void transfer() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            };
        }
    }
    /** Initialize your data structure here. */
    public LC_232() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        transfer();
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        transfer();
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty()&&s2.isEmpty();
    }
}
