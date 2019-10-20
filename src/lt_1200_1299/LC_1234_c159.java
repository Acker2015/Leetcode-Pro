package lt_1200_1299;

/**
 * 1234. Replace the Substring for Balanced String
 *
 * sliding window - 做法可能需要想一下
 * 为什么是滑动窗口？
 * 对于QWER,如果其中一个字符出现次数大于n/4, 那么大于n/4的部分必须要出现在替换的子串中
 *
 * 比如n/4=5, 但是s中有7个Q，那么需要替换的子串中至少要包含两个Q,
 * 因为这两个Q必须要替换成其他字符才能够保证最后每个字符出现次数为n/4
 *
 * 所以就是一个滑动窗口的问题，除去窗口内的元素。剩下元素中每个字符的数量都必须小于等于n/4
 */
public class LC_1234_c159 {

    static class Solution1 {
        private static char[] symbols = {'Q','W','E','R'};
        public int balancedString(String s) {
            int[] counts = new int[128];
            char[] chs = s.toCharArray();
            int n = chs.length;
            for (char c: chs) {
                counts[c]++;
            }
            int ans = 0;
            for (char c: symbols) {
                counts[c] -= n/4;
                ans += Math.max(counts[c], 0); // 计算多出来的字符个数
            }
            int i = 0, j = 0, minLen = n;
            while (j < n) {
                char c = chs[j++];
                counts[c]--;
                if (counts[c] >= 0) {
                    ans--;
                }
                while (ans==0 && i <= j && i < n) {
                    counts[chs[i]]++;
                    if (counts[chs[i]] > 0) {
                        ans++;
                    }
                    minLen = Math.min(minLen, j-i);
                    i++;
                }
            }
            return minLen;
        }
    }

    /**
     * Solution2
     * 更加简洁
     * 原理类似滑动窗口
     */
    static class Solution2 {
        public int balancedString(String s) {
            int[] counts = new int[128];
            char[] chs = s.toCharArray();
            for (char c: chs) {
                counts[c]++;
            }
            int i = 0, j = 0, minLen = chs.length, n = chs.length;
            while (j < chs.length) {
                counts[chs[j++]]--;
                // 考虑窗口大小可能为0的情况
                while (i <= j && i < n && counts['Q']<=n/4 && counts['W']<=n/4 && counts['E']<=n/4 && counts['E']<=n/4) {
                    minLen = Math.min(minLen, j-i);
                    counts[chs[i++]]++;
                }
            }
            return minLen;
        }
    }



    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.balancedString("QQQQ"));
    }




}
