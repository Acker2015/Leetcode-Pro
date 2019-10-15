package lt_300_399;

/**
 * [338] Counting Bits
 */
public class LC_338 {
    /**
     * DP
     * 通过将bit位的最后一位置为0，找到更小的数来计算
     * j&(j-1)
     * dp[j] = dp[j&(j-1)] + 1
     */
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        if (num <= 0) {
            return result;
        }
        for (int i = 1; i <= num; ++i) {
            result[i] = result[i&(i-1)] + 1;
        }
        return result;
    }
}
