package secondround.sr1_300;

import java.util.Stack;

/**
 * stack
 */
public class sr_020 {
    private boolean match(char left, char right) {
        return (left=='('&&right==')') ||
                (left=='['&&right==']') ||
                (left=='{'&&right=='}');
    }
    public boolean isValid(String s) {
        char[] chs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c: chs) {
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == ')') {
                if (!stack.isEmpty() && match(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
