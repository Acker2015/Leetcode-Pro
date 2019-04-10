package lt_200_299;


import javafx.util.Pair;

import java.util.Stack;

/**
 * [224] Basic Calculator
 */
public class LC_224 {
    /**
     * 把加减操作看作是正负数的求和操作，跟踪符号
     * 遇到括号表达式的时候使用栈来记录之前的临时结果和中间符号
     *
     * Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
     Only 5 possible input we need to pay attention:

     digit: it should be one digit from the current number
     '+': number is over, we can add the previous number and start a new number
     '-': same as above
     '(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
     ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
     Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
     */
    public int calculate(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        char[] chs = s.toCharArray();
        int i = 0, sign = 1, result = 0, number = 0;
        while (i < chs.length) {
            if (chs[i]==' ') {
                i++;
                continue;
            }
            if (chs[i] == '(') {
                stack.push(new Pair<>(result, sign));
                result = 0;
                sign = 1;
                number = 0;
                i++;
            } else if (chs[i] == ')') {
                result += sign*number;
                Pair<Integer, Integer> pair = stack.pop();
                result = pair.getKey() + pair.getValue()*result;
                sign=1;
                number=0;
                i++;
            } else if (chs[i] == '+' || chs[i] == '-') {
                result += sign*number;
                sign = chs[i]=='+' ? 1:-1;
                number = 0;
                i++;
            } else {
                int j = i+1;
                while (j < chs.length && Character.isDigit(chs[j])) j++;
                number = Integer.parseInt(s.substring(i, j));
                i = j;
                //number = 10*number + (chs[i++]-'0');
            }
        }
        return result+sign*number;
    }
}
