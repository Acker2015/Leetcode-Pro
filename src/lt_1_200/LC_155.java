package lt_1_200;

import java.util.Stack;

/**
 * 核心思想：
 * 在push的过程中，如果发现有更小的数出现，那么将之前的最小值和新的最小值一起push进去(保持当前最小值在栈中的前一个值是次最小值)
 * 同理在pop过程中，如果栈顶的值为最小值，那么pop两次，第二次的pop结果就是新的最小值
 * 绝妙！
 */
public class LC_155 {
    private Stack<Integer> stack;
    private int min;
    /** initialize your data structure here. */
    public LC_155() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }
    /**
     * think about pushing duplicate x
     * if x <= min, push twice. push(oldMin), push(newMin)
     *
     * keep if second min is following the min val in stack.
     */
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    /**
     * if top is min, pop twice, the value of second pop is the new min after this pop procedure
     */
    public void pop() {
        if (stack.pop()==min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    /**
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> Returns -3.
     * minStack.pop();
     * minStack.top();      --> Returns 0.
     * minStack.getMin();   --> Returns -2.
     * @param args
     */
    public static void main(String ...args) {
        LC_155 lc_155 = new LC_155();
        lc_155.push(-2);
        lc_155.push(0);
        lc_155.push(-3);
        System.out.println(lc_155.getMin());
        lc_155.pop();
        System.out.println(lc_155.top());
        System.out.println(lc_155.getMin());
    }
}
