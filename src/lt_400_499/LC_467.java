package lt_400_499;

/**
 * [467] Unique Substrings in Wraparound String
 * DP
 *
 * 反思一下一开始为什么对于去重逻辑老想着使用set
 */
public class LC_467 {
    /**
     * dp[i] 表示p[i]结尾的right-sub-sequence
     * 这里防止子串重复的方法是直接记录固定字符结尾的最大子串即可
     * 比如 abc -> 记录 c=3, 这样表示以c结尾的有三个子串
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        int len = p.length();
        if (len <= 1) return len;
        int[] mem = new int[26];
        char[] chs = p.toCharArray();
        mem[chs[0]-'a'] = 1;
        int maxContinousLen = 1;
        for (int i = 1; i < len; ++i) {
            if (chs[i]-chs[i-1] == 1 || chs[i-1]-chs[i]==25) {
                maxContinousLen += 1;
            } else {
                maxContinousLen = 1;
            }
            int idx = chs[i]-'a';
            mem[idx] = Math.max(mem[idx], maxContinousLen);
        }
        int sum = 0;
        for (int i = 0; i < 26; ++i) {
            sum += mem[i];
        }
        return sum;
    }
}
