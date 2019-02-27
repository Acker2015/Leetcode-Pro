package lt_700_799;

public class LC_746 {
	/**
	 * dynamic programming
	 * dp[i]表示到达i-th stair需要的花费
	 * dp[i] = min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1])
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairs(int[] cost) {
	    if (cost.length <= 2) return 0;
	    int[] dp = new int[cost.length+1];
	    dp[0]=dp[1]=0;
	    for (int i=2; i < dp.length; ++i) {
	        dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
	    }
	    return dp[dp.length-1];
	}
	/**
	 * dp[i] 表示经过i-th stair向上的花费
	 * dp[0]=cost[0]
	 * dp[1]=cost[1]
	 * 
	 * dp[i] = cost[i] + min(dp[i-1], dp[i-2])
	 * @param cost
	 * @return
	 */
	public int minCostClimbingStairs2(int[] cost) {
		if (cost.length <= 2) return 0;
		int[] dp = new int[cost.length];
		dp[0] = cost[0];
		dp[1] = cost[1];
		for (int i = 2; i < dp.length; ++i) {
			dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
		}
		return Math.min(dp[dp.length-1], dp[dp.length-2]);
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		LC_746 lc_746 = new LC_746();
		System.out.println(lc_746.minCostClimbingStairs2(nums));

	}

}
