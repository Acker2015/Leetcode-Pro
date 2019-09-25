package lt_400_499;

/**
 * [486] Predict the Winner
 */
public class LC_486 {


    /**
     * DP+DFS
     *
     * Solution1: So assuming the sum of the array it SUM, so eventually player1 and player2 will split the SUM between themselves.
     * For player1 to win, he/she has to get more than what player2 gets.
     * If we think from the prospective of one player, then what he/she gains each time is a plus, while, what the other player gains each time is a minus.
     * Eventually if player1 can have a >0 total, player1 can win.
     *
     * complexity
     * Space: O(n), the recursive can go as deep as n.
     * Time: Without cache/mem, it is O(2^n), because each node can have 2 children.
     * With cache/mem, it is O(n^2), same as DP, because at most, we will calculate all index * index combinations and others are just read from cache. There are n ^ 2 of possible combinations.
     */
    public static class Solution1 {
        /**
         * 返回两人选择之间的差值
         * @param nums
         * @param left
         * @param right
         * @return
         */
        private int search(int[] nums, int left, int right, Integer[][] mem) {
            if (left > right) return 0;
            if (mem[left][right] != null) {
                return mem[left][right];
            }
            int chooseLeft = search(nums, left+1, right, mem);
            int chooseRight = search(nums, left, right-1, mem);

            int diff = Math.max(nums[left]-chooseLeft, nums[right]-chooseRight);
            mem[left][right] = diff;
            return diff;
        }
        public boolean PredictTheWinner(int[] nums) {
            int len = nums.length;
            Integer[][] mem = new Integer[len][len];
            int diff = search(nums, 0, len-1, mem);
            System.out.println(diff);
            return diff >= 0;
        }
    }

    public static class Solution2 {
        /**
         * dp[i][j] means more scores that the first player can get from [i, j] than the other player.
         *
         * so dp[i][j] = max{nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]}
         * @param nums
         * @return
         */
        public boolean PredictTheWinner(int[] nums) {
            int len = nums.length;
            int[][] dp = new int[len][len];
            for (int i = 0; i < len; ++i) {
                dp[i][i] = nums[i];
            }
            for (int gap = 2; gap <= len; ++gap) {
                for (int i = 0; i < len+1-gap; ++i) {
                    int j = i + gap - 1;
                    dp[i][j] = Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
                }
            }
            return dp[0][len-1] >= 0;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,5,2};
        int[] arr2 = {1,5,233,7};
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        System.out.print(solution1.PredictTheWinner(arr) + "\t");
        System.out.println(solution2.PredictTheWinner(arr));

        System.out.print(solution1.PredictTheWinner(arr2) + "\t");
        System.out.println(solution2.PredictTheWinner(arr2));
    }

}
