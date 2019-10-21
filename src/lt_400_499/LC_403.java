package lt_400_499;

import java.util.HashMap;
import java.util.Map;

/**
 * 403. Frog Jump
 * solution1: DP
 * solution2: DFS+Memorization
 */
public class LC_403 {

    /**
     * DP
     * dp[i][k] 表示是否可以通过上一步长为k到达第i个stone
     *
     * 那么当从stones[j]跳到stones[i]的时候
     * gap= stones[i]-stones[j]
     * 所以
     * dp[i][gap] = dp[j][gap] || dp[j][gap-1] || dp[j][gap+1]
     *
     * O(n^2)
     */
    static class Solution1 {
        public boolean canCross(int[] stones) {
            int len = stones.length;
            if (len < 2 || stones[1]-stones[0] != 1) return false;
            // dp[i][k] 表示是否可以通过上一步长为k到达第i个stone
            boolean[][] dp= new boolean[len][len];
            dp[0][0] = true;
            dp[1][1] = true;
            for (int i = 2; i < len; ++i) {
                for (int j = 1; j < i; ++j) {
                    int gap = stones[i]-stones[j];
                    if (gap > i) continue;
                    dp[i][gap] = dp[j][gap] || (gap<len-1 && dp[j][gap+1]) || (gap>=1 && dp[j][gap-1]);
                }
            }
            for (int i = 0; i < len; ++i) {
                if (dp[len-1][i]) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 使用DFS搜索
     */
    static class Solution2 {
        public boolean canCross(int[] stones) {
            Map<Integer, Boolean> dp = new HashMap<>();
            int len = stones.length;
            if (len < 1 || stones[1]-stones[0]!=1) return false;
            return canCross(stones, 0, 0, dp);
        }

        /**
         * 是否可以到达
         * @param stones
         * @param pos       达到的stone索引
         * @param k         达到stone索引的k值(step)
         * @param dp        记忆数组
         * @return
         */
        private boolean canCross(int[] stones, int pos, int k, Map<Integer, Boolean> dp) {
            // 因为stone的个数最多为1100个，所以pos肯定小于2^11(2048)
            // memorization需要同时考虑pos和来源的k
            int key = pos | (k << 11);
            if (dp.containsKey(key)) {
                return dp.get(key);
            }
            for (int i = pos+1; i < stones.length; ++i) {
                int gap = stones[i]-stones[pos];
                if (gap < k-1) continue;
                if (gap > k+1) {
                    dp.put(key, false);
                    return dp.get(key);
                }
                if (canCross(stones, i, gap, dp)) {
                    dp.put(key, true);
                    return dp.get(key);
                }
            }
            return pos==stones.length-1;
        }
    }


    public static void main(String[] args) {
        int[] stones = {0,1,3,4,5,7,9,10,12};
        Solution1 solution = new Solution1();
        System.out.println(solution.canCross(new int[]{0,1,2,3,4,7}));
    }
}
