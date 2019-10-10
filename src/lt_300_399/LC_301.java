package lt_300_399;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [301] Remove Invalid Parentheses
 * solution1: backtracking
 * solution2: 最高效的删除操作
 * solution3: BFS(对每一个字符尝试产出，看是否可用)  （https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution）
 *              T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
 */
public class LC_301 {

    /**
     * solution1:暴力回溯
     */
    static class Solution1 {
        /**
         * 先进行可用性测试，记录非法左右括号的个数(后边需要检测删除的个数),返回合法的最大字符长度
         */
        private int validForMaxLength(char[] chs) {
            int leftNum = 0;
            int invalidRightNum = 0;
            for (char c: chs) {
                if (c == '(') {
                    leftNum+=1;
                } else if (c == ')') {
                    if (leftNum > 0) {
                        leftNum--;
                    } else {
                        invalidRightNum++;
                    }
                }
            }
            return chs.length - leftNum - invalidRightNum;
        }

        /**
         * idx表示索引位置，leftNoMatchNum表示遍历到idx未匹配右括号的左括号个数，maxLen表示最大合法括号对的长度，builder存放当前结果
         * 1. 合法Parentheses的表现为 idx遍历结束 and builder中的字符长度等于maxLen
         * 2. 开头剪枝
         *      2.1 leftNoMatchNum<0, 表示出现了未匹配左括号的右括号,此为不合法的情况
         *      2.2 builder.length()+(chs.length - idx) < maxLen， 表示后续得到的builder中字符长度肯定会小于maxLen
         * 3. 对于回溯过程中出现的'(',')'有两种选择
         *      3.1 选择，根据括号类型对leftNoMatchNum进行增减
         *      3.2 丢弃，尝试下一个索引
         */
        private void backTracking(char[] chs, int idx, int leftNoMatchNum, StringBuilder builder, int maxLen, Set<String> retList) {
            if (leftNoMatchNum < 0 || builder.length()+(chs.length - idx) < maxLen) return;
            if (idx == chs.length) {
                // builder.length() == maxLen
                if (leftNoMatchNum == 0) {
                    retList.add(builder.toString());
                }
                return;
            }
            if (chs[idx] != '(' && chs[idx] != ')') {
                builder.append(chs[idx]);
                backTracking(chs, idx+1, leftNoMatchNum, builder, maxLen, retList);
                builder.deleteCharAt(builder.length()-1);
            } else {
                builder.append(chs[idx]);
                backTracking(chs, idx+1, leftNoMatchNum+(chs[idx]=='('?1:-1), builder, maxLen, retList);
                builder.deleteCharAt(builder.length()-1);

                backTracking(chs, idx+1, leftNoMatchNum, builder, maxLen, retList);
            }
        }

        public List<String> removeInvalidParentheses(String s) {
            Set<String> retSet = new HashSet<>();
            char[] chs = s.toCharArray();
            int maxLen = validForMaxLength(chs);
            backTracking(chs, 0, 0, new StringBuilder(), maxLen, retSet);
            if (retSet.size() == 0) {
                retSet.add("");
            }
            return new ArrayList<>(retSet);
        }
    }


    /**
     * Key Points:
     1. Generate unique answer once and only once, do not rely on Set.
     2. Do not need pre-process.
     3. Runtime 3 ms.

     Explanation:
     We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
     The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.


     To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix.
     However, if we remove any one, we will generate duplicate results,
     for example: s = ()), we can remove s[1] or s[2] but the result is the same ().
     Thus, we restrict to remove the first ) in a series of consecutive )s.

     After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
     For this, we keep tracking the last removal position and only remove ‘)’ after that.

     Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
     The answer is: do the same from right to left.
     However a cleverer idea is: reverse the string and reuse the code!
     Here is the final implement in Java.
     */
    public static class Solution2 {
        public List<String> removeInvalidParentheses(String s) {
            List<String> ans = new ArrayList<>();
            remove(s, ans, 0, 0, new char[]{'(', ')'});
            return ans;
        }

        /**
         * remove操作
         * @param s
         * @param ans
         * @param last_i        stack已经计数的位置
         * @param last_j        删除元素的起始位置(本轮删除元素需要从last_j处起始)
         * @param par
         */
        public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
            for (int stack = 0, i = last_i; i < s.length(); ++i) {
                if (s.charAt(i) == par[0]) stack++;
                if (s.charAt(i) == par[1]) stack--;
                if (stack >= 0) continue;
                for (int j = last_j; j <= i; ++j) {
                    if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                        String newS = s.substring(0, j) + s.substring(j + 1);
                        remove(newS, ans, i, j, par);
                    }
                }
                // 这里的return很重要, 防止上述inner-loop重复删除字符造成duplicate
                return;
            }
            String reversed = new StringBuilder(s).reverse().toString();
            if (par[0] == '(') // finished left to right
                remove(reversed, ans, 0, 0, new char[]{')', '('});
            else // finished right to left
                ans.add(reversed);
        }
    }
}
