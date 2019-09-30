package lt_1200_1299;

import java.util.Stack;

/**
 * 1209 - Remove All Adjacent Duplicates in String II
 * Contest 156
 * Q3
 *
 * Stack
 *
 * 使用array代替栈效率更高
 *
 */
public class LC_1209_c156 {
    private static class Solution1 {
        // stack
        public String removeDuplicates(String s, int k) {
            Stack<Character> stack = new Stack<>();
            Stack<Integer> numStack = new Stack<>();
            char[] chs = s.toCharArray();
            for (char c : chs) {
                int num = (!stack.isEmpty() && stack.peek() == c) ? numStack.peek() + 1 : 1;
                if (num == k) {
                    // pop k-1 nums
                    while (--num > 0) {
                        stack.pop();
                        numStack.pop();
                    }
                } else {
                    stack.push(c);
                    numStack.push(num);
                }
            }
            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.append(stack.pop());
            }
            return builder.reverse().toString();
        }
    }

    private static class Solution2 {
        /**
         * 使用数组代替栈，这样在遇到连续k个不需要一个个出栈，直接将栈顶指针重置即可
         * input:  s = "deeedbbcccbdaa", k = 3
         * output: "aa"
         * @param s
         * @param k
         * @return
         */
        public String removeDuplicates(String s, int k) {
            int len = s.length();
            char[] charStack = new char[len];
            int[] numStack = new int[len];
            int idx = 0;
            char[] chs = s.toCharArray();
            for (char c : chs) {
                int num = (idx > 0 && charStack[idx - 1] == c) ? numStack[idx - 1] + 1 : 1;
                if (num == k) {
                    idx = idx - (num - 1);
                } else {
                    charStack[idx] = c;
                    numStack[idx] = num;
                    idx++;
                }
            }
            return String.valueOf(charStack, 0, idx);
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.removeDuplicates("deeedbbcccbdaa", 3));
    }
}
