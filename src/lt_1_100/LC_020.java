package lt_1_100;

import java.util.Stack;
/**
 * 判断括号匹配字符串是否合法
 * @author Acker
 *
 */
public class LC_020 {
	public boolean isValid(String s) {
        if(s==null||s.length()<=0) return true;
        Stack<Character> stack = new Stack<>();
        char[] symbols = s.toCharArray();
        for(char c: symbols) {
            if (c=='(' || c=='[' || c=='{') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) return false;
            boolean flag = (c==')'&&stack.peek()=='(') ||
                (c==']'&&stack.peek()=='[') || (c=='}'&&stack.peek()=='{');
            if (flag) {
                stack.pop();
            } else {
                return flag;
            }
        }
        return stack.isEmpty();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
