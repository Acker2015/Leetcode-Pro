package lt_1_200;

import java.util.Stack;

public class LC_071 {
	public String simplifyPath(String path) {
        int i = 0;
        Stack<String> stack = new Stack<>();
        while (i < path.length()) {
            if (path.charAt(i)=='/') {
                i++;
                continue;
            }
            int j = i;
            while (j < path.length() && path.charAt(j)!='/') j++;
            String subStr = path.substring(i, j);
            if (subStr.equals(".")) {
                i = j;
                continue;
            } else if (subStr.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(subStr);
            }
            i = j;
        }
        if (stack.isEmpty()) return "/";
        String result = "";
        while(!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        return result;
    }
	public static void main(String[] args) {
		String tmp = "/../";
		LC_071 lc_071 = new LC_071();
		System.out.println(lc_071.simplifyPath(tmp));

	}

}
