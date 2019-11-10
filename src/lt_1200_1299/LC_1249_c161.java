package lt_1200_1299;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 *
 * - It is the empty string, contains only lowercase characters, or
 * - It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * - It can be written as (A), where A is a valid string.
 *
 *
 Example 1:
 Input: s = "lee(t(c)o)de)"
 Output: "lee(t(c)o)de"
 Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

 Example 2:
 Input: s = "a)b(c)d"
 Output: "ab(c)d"

 Example 3:
 Input: s = "))(("
 Output: ""
 Explanation: An empty string is also valid.

 Example 4:
 Input: s = "(a(b(c)d)"
 Output: "a(b(c)d)"
 */
public class LC_1249_c161 {
    /**
     * String
     * two-pass
     *
     * 第一轮，得到可以正确匹配的parenthesis "()"pair数量，放到变量match中
     * 第二轮，进行最后字符串的输出，按照正常匹配方式，但是匹配的parenthesis数量不能超过match
     *
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        StringBuilder builder = new StringBuilder();
        char[] chs = s.toCharArray();
        int ans = 0, match = 0, i = 0;
        while (i < chs.length) {
            if (chs[i] == '(') {
                ans++;
            } else if (chs[i] == ')' && ans > 0) {
                match++;
                ans--;
            }
            i++;
        }
        ans = 0; i = 0;
        while (i < chs.length) {
            if (Character.isAlphabetic(chs[i])) {
                builder.append(chs[i]);
            } else if (chs[i] == '(') {
                ans++;
                if (match-- > 0) {
                    builder.append(chs[i]);
                }
            } else if (ans > 0) {
                ans--;
                builder.append(chs[i]);
            }
            i++;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s1 = "lee(t(c)o)de)";
        String s2 = "a)b(c)d";
        String s3 = "))((";
        String s4 = "(a(b(c)d)";

        LC_1249_c161 solution = new LC_1249_c161();
        System.out.println(solution.minRemoveToMakeValid(s1));
        System.out.println(solution.minRemoveToMakeValid(s2));
        System.out.println(solution.minRemoveToMakeValid(s3));
        System.out.println(solution.minRemoveToMakeValid(s4));
    }
}
