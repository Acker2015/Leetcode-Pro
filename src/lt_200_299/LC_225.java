package lt_200_299;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [225] Implement stack using Queues
 *
 */
public class LC_225 {
    Queue<Integer> q1;
    Queue<Integer> q2;
    /** Initialize your data structure here. */
    public LC_225() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    private void transfer() {
        if (q2.isEmpty()) {
            while (q1.size()>1) {
                q2.offer(q1.poll());
            }
            Queue<Integer> tmpQ = q1;
            q1 = q2;
            q2 = tmpQ;
        }
    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (!q2.isEmpty()) {
            q1.offer(q2.poll());
        }
        q2.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        transfer();
        return q2.poll();
    }

    /** Get the top element. */
    public int top() {
        transfer();
        return q2.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty()&&q2.isEmpty();
    }
}
