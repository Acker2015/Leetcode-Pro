package lt_400_499;

import java.util.Stack;

/**
 * [402] Remove K Digits
 */
public class LC_402 {
    /**
     * greedy
     * whenever you meet the char, if it is less then the previous char, the current char would be discarded.
     * just use a stack to help resolve this problem.
     */
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        char[] chs = num.toCharArray();
        int len = num.length();
        int i = 0;
        while (i < len) {
            //whenever meet a digit which is less than the previous digit, discard the previous one
            if (k > 0 && !stack.isEmpty() && chs[i] < stack.peek()) {
                stack.pop();
                k--;
            } else {
                stack.push(chs[i++]);
            }
        }
        // corner case like "1111"
        while (k-- > 0) {
            stack.pop();
        }
        //construct the number from the stack
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        // delete tail zero
        while (builder.length()>1 && builder.charAt(builder.length()-1)=='0') {
            builder.deleteCharAt(builder.length()-1);
        }
        builder.reverse();
        return builder.toString();
    }
}
