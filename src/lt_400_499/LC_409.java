package lt_400_499;

/**
 * [409] Longest Palindrome
 */
public class LC_409 {
    /**
     * 出现次数为偶数的字符全部都能取到
     * 出现次数为奇数的字符全部去偶数个
     * 如果有出现奇数次数的字符，最后结果+1
     *
     * 即最多只能有一个奇数次数的字符出现
     */
    public int longestPalindrome(String s) {
        int[] mem = new int[128];
        char[] arr = s.toCharArray();
        for (char c: arr) {
            mem[c]++;
        }
        int longestNum = 0;
        boolean hasOdd = false;
        for (int i = 0; i < 128; ++i) {
            if (mem[i]%2==1) {
                hasOdd = true;
                longestNum += (mem[i]/2)*2;
            } else {
                longestNum += mem[i];
            }
        }
        return hasOdd ? longestNum+1:longestNum;
    }
}
