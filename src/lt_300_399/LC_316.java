package lt_300_399;


public class LC_316 {
    /**
     * Given the string s, the greedy choice (i.e., the leftmost letter in the answer) is the smallest s[i], s.t.
     * the suffix s[i .. ] contains all the unique letters. (Note that, when there are more than one smallest s[i]'s,
     * we choose the leftmost one. Why? Simply consider the example: "abcacb".)
     * After determining the greedy choice s[i], we get a new string s' from s by

     removing all letters to the left of s[i],
     removing all s[i]'s from s.
     We then recursively solve the problem w.r.t. s'.

     The runtime is O(26 * n) = O(n).


     枚举字符串前缀，直到遇到首个唯一字符为止，从前缀中挑选出最小的字符作为首字符。
     然后从剩余字符串中移除所有与首字母相同的字母。
     重复此过程，直到选出所有唯一字符为止。
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
}
