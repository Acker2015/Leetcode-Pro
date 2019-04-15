package lt_300_399;

/**
 * [395] Longest Substring with At Least K Repeating Characters
 */
public class LC_395 {
    private char[] chs;
    public int longestSubstring(int left, int right, int k) {
        if (right-left+1 < k) return 0;
        int[] temp = new int[26];
        for (int i = left; i <= right; ++i) {
            temp[chs[i]-'a']++;
        }
        for (int j = 0; j < 26; ++j) {
            // choose division point
            if (temp[j] > 0 && temp[j] < k) {
                for (int i = left; i <= right; ++i) {
                    if ((chs[i]-'a')==j) {
                        int maxL = longestSubstring(left, i-1, k);
                        int maxR = longestSubstring(i+1, right, k);
                        /**
                         * 想一下这里为什么会直接返回，而不是将所有的pivot都遍历完，求出最大值?
                         *
                         * 因为longestSubstring(left, i-1, k)和longestSubstring(i+1, right, k)会包括其他情况，
                         * 其中的更多pivot会在这两个子范围会继续分割，而不用计算很多重复情况
                         * 因为如果一个字符在[left, right]之间的个数不足k个，那么在[left,i-1] or [i+1, right]中同样也不会超过k个
                         * 这样他总会被找到
                         */
                        return Math.max(maxL, maxR);
                    }
                }
            }
        }
        return right-left+1;
    }

    /**
     * 解法一
     * divide and conquer(recursion) solution
     * 如果在[left, right]中存在字符的个数不足k个，那么最大长度只会出现以该字符作为中间节点分割的两个子串
     * solution to find longest:
     *      IF { num of s[i] < k in [left, right] }
     *          find longest in [left, i-1] and [i+1, right]
     *      else {
     *          longest = right-left+1;
     *      }
     *
     * 注意点，divide过程中，只需要找到其中一个不满足k个的字符分割即可，不用每一个都左右分割，因为其他情况在分割过程中还会遇到
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring1(String s, int k) {
        chs = s.toCharArray();
        return longestSubstring(0, chs.length-1, k);
    }

    /**
     * solution2
     * 双层循环，O(n^2)
     *
     * 不断判断[i, j]是否为longestSubstring
     * 1. 使用长度为26的int数组来记录每个字符出现的次数
     * 2. 由于s中都是小写字母，所以可以使用26位bit为来记录是否出现k次，int变量ans来记录
     *      如果某一位bit为1，表示对应的字符出现次数不足k次
     *      如果某一位bit为0，表示对应的字符没有出现或者是出现次数>=k
     *    所以在第二次遍历过程中，如果ans为0表示全部出现的字符都满足出现k次以上
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring2(String s, int k) {
        int i = 0, maxLen = 0;
        chs = s.toCharArray();
        while (i+k <= chs.length) {
            int[] temp = new int[26];
            int ans = 0, nextId = i+1;
            for (int j = i; j < chs.length; ++j) {
                int idx = chs[j]-'a';
                // 对于新出现的字符，在ans中记录对应的bit位
                if (temp[idx] == 0) {
                    ans |= (1<<idx);
                }
                temp[idx]++;
                // 对于满足k次要求的字符，在ans中重置bit位为0
                if (temp[idx] >= k) {
                    ans &= (~(1<<idx));
                }
                // ans为0表示其中的字符出现次数都满足k次的限制
                if (ans == 0) {
                    maxLen = Math.max(maxLen, j-i+1);
                    nextId = j+1;
                }
            }
            i = nextId;
        }
        return maxLen;
    }

    /**
     * Solution3
     * slide window
     * 对子串的可能出现的不同字符的个数进行枚举， h为本轮滑动窗口中不同字符的个数。 从1到26进行枚举
     * ->
     *      i，j记录滑动窗口的左右索引
     *      unique记录窗口里不同的字符个数
     *      numMoreThanK记录窗口里字符数量超过K的个数
     *
     *      显然 当unique==h && unique==numMoreThanK 的时候说明窗口里是满足条件的substring
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring3(String s, int k) {
        int maxLen = 0;
        for (int h = 1; h <= 26; ++h) {
            int i = 0, j = 0, unique = 0, numMoreThanK=0;
            int[] temp = new int[26];
            while (j < s.length()) {
                if (unique <= h) {
                    int cv = s.charAt(j)-'a';
                    if (temp[cv] == 0) unique++;
                    temp[cv]++;
                    if (temp[cv] == k) numMoreThanK++;
                    j++;
                } else {
                    int cv = s.charAt(i)-'a';
                    if (temp[cv] == k) numMoreThanK--;
                    temp[cv]--;
                    if (temp[cv] == 0) unique--;
                    i++;
                }
                if (unique==h && unique==numMoreThanK) {
                    maxLen = Math.max(maxLen, j-i);
                }
            }
        }
        return maxLen;
    }


    public static void main(String ...args) {
        String s = "aaabb";
        LC_395 lc_395 = new LC_395();
        System.out.println(lc_395.longestSubstring3(s, 3));
    }
}
