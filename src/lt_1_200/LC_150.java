package lt_1_200;

import java.util.Stack;

/**
 * [150] Evaluate Reverse Polish Notation
 */
public class LC_150 {
    private int cal(int a, int b, String op) {
        switch (op) {
            case "+": return a+b;
            case "-": return a-b;
            case "*": return a*b;
            case "/": return a/b;
            default: return 0;
        }
    }
    /**
     * time complexity O(n), n is the length of tokens
     * space O(d), d is the number of digits in tokens
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str: tokens) {
            if ("+-*/".contains(str)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(cal(b,a,str));
            }
            else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.size()==1 ? stack.pop():0;
    }
}
