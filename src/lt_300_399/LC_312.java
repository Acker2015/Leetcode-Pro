package lt_300_399;


/**
 [312] Burst Balloons

 Input: [3,1,5,8]
 Output: 167
 Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class LC_312 {

    // Solution1 - DP - down_to_top
    static class Solution1 {
        /**
         * 使用dp[l][r]表示扎破(l, r)范围内所有气球获得的最大硬币数，不含边界；
         * 一开始对输入的nums数组左右两边都分别加上1，那么索引0和n-1处就是加上去的1
         *
         * 所以最后结果就是求dp[0][n-1]表示0和n-1范围内所有气球获取的最大硬币数，很显然这里都要的是自底向上，
         * 就是说从l,r距离很小往l和r距离较大的地方循环，那么就需要设置一个l，r的间距k来表示，由于l和r之间最少一个气球，所以k从2开始遍历。
         *
         * 如何求dp[l][r],这里使用l和r之间的最后一个被扎破的气球作为分类，在(l, r)区间burst气球的方案可以按照最后剩下的那个气球作为分类，
         * 如果最后剩下的是第m个气球(l<m<r)，
         * 那么dp[l][r] = nums[l] * nums[m] * nums[r] (关于m)
         所以在遍历m的过程中 dp[l][r] = max( dp[l][r], dp[l][m] + dp[m][r] + nums[l] * nums[m] * nums[r] );

         *
         * 5ms
         * @param nums
         * @return
         */
        public int maxCoins(int[] nums) {
            if (nums.length <= 0) return 0;
            int[] refactor = new int[nums.length+2];
            for (int i = 1; i <= nums.length; ++i) {
                refactor[i] = nums[i-1];
            }
            refactor[0] = refactor[nums.length+1] = 1;
            int n = refactor.length;
            int[][] dp = new int[n][n];
            for (int k = 2; k < n; ++k) {
                for (int l = 0; l< n-k; ++l) {
                    int r = l + k;
                    for (int m = l+1; m < r; ++m) {
                        dp[l][r] = Math.max(dp[l][r], refactor[l]*refactor[m]*refactor[r]+dp[l][m]+dp[m][r]);
                    }
                }
            }
            return dp[0][n-1];
        }
    }


    static class Solution2 {
        /**
         * 上述方法 top-to-down
         *
         * divide and conquer
         * @param nums
         * @return
         */
        public int maxCoins(int[] nums) {
            if (nums.length <= 0) return 0;
            int[] refactor = new int[nums.length+2];
            for (int i = 1; i <= nums.length; ++i) {
                refactor[i] = nums[i-1];
            }
            refactor[0] = refactor[nums.length+1] = 1;
            int n = refactor.length;
            return burst(refactor, 0, refactor.length-1, new int[n][n]);
        }
        public int burst(int[] refactor, int l, int r, int[][] mem) {
            if (l+1 == r) {
                return 0;
            }
            if (mem[l][r] > 0) {
                return mem[l][r];
            }
            int ans = 0;
            for (int m = l+1; m < r; ++m) {
                ans = Math.max(ans, refactor[l]*refactor[m]*refactor[r]+
                    burst(refactor, l, m, mem) + burst(refactor, m, r, mem));
            }
            mem[l][r] = ans;
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        Solution2 solution = new Solution2();
        System.out.println(solution.maxCoins(nums));
    }
}
