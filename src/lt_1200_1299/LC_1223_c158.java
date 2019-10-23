package lt_1200_1299;


import java.util.Arrays;

/**
 *
 * 1223. Dice Roll Simulation
 *
 * 题目比较难懂
 * 意思就是模拟器每次可以产生一个1-6的元素，与之前产生的元素构成一个序列
 * 但是1-6这6个元素每个元素在序列中出现的最大连续次数被记录到rollMax中。(注意是要求连续出现次数)
 *
 *
 *
 * dp[x][i][k] 表示第x轮中序列以连续k个i作为结尾序列的个数
 * 比如序列(13222) 会被记录到dp[5][2][3]表示第5轮,以2结尾,尾部连续2的个数为3
 *
 * 状态转移，若第x轮新加入元素为j
 * 1. dp[x][j][k+1] += dp[x-1][i][k]        如果i==j && j的允许最大出现连续次数 >= k+1
 * 2. dp[x][j][1] += dp[x-1][i][k]          如果i!=j && j的允许最大出现连续次数 >= 1
 */
public class LC_1223_c158 {
    private static final int MOD = 1000000007;


    /**
     * DP解法
     */
    static class Solution1 {
        /**
         * dp[x][i][k]
         * x表示第几个数
         * i表示以i为结尾
         * k表示以1位结尾的尾连续i的个数
         */
        public int dieSimulator(int n, int[] rollMax) {
            if (n == 0) return 0;
            int[][][] dp = new int[n+1][7][16];
            for (int i = 1; i <= 6; ++i) {
                if (rollMax[i-1] > 0) {
                    // 第一位数以i结尾的最大长度为1，并且能够产生的sequence个数
                    dp[1][i][1] = 1;
                }
            }
            for (int x = 2; x <= n; ++x) {
                for (int i = 1; i <= 6; ++i) {
                    for (int k = 1; k <= 15; ++k) {
                        for (int j = 1; j <= 6; ++j) {
                            // 如果j处的rollMax为0 或者连续已经达到rollMax[j]上限，那么就不能继续添加此元素
                            if (rollMax[j-1] <= 0 || (i==j && k >= rollMax[j-1])) {
                                continue;
                            }
                            if (i == j) {
                                dp[x][i][k+1] = (dp[x][i][k+1] + dp[x-1][i][k]) % MOD;
                            } else {
                                // 新加入j
                                dp[x][j][1] = (dp[x][j][1] + dp[x-1][i][k]) % MOD;
                            }
                        }
                    }
                }
            }
            int num = 0;
            for (int i = 1; i <= 6; ++i) {
                for (int k = 1; k <= 15; ++k) {
                    num = (num + dp[n][i][k]) % MOD;
                }
            }
            return num;
        }
    }

    /**
     * top-down
     * dfs+mem
     *
     * 写起来更容易
     */
    static class Solution2 {
        public int dieSimulator(int n, int[] rollMax) {
            int[][][] mem = new int[n+1][7][16];

            return cal(n, 0, 0, rollMax, mem);
        }

        private int cal(int leftRolls, int tail, int consectiveNum, int[] rollMax, int[][][] mem) {
            if (leftRolls <= 0) return 1;
            int ans = 0;
            if (mem[leftRolls][tail][consectiveNum] > 0) {
                return mem[leftRolls][tail][consectiveNum];
            }
            for (int k = 1; k <= 6; ++k) {
                if (k != tail && rollMax[k-1] >= 1) {
                    ans = (ans + cal(leftRolls-1, k, 1, rollMax, mem))%MOD;
                }
                if (k == tail && rollMax[k-1] >= consectiveNum+1) {
                    ans = (ans + cal(leftRolls-1, k, consectiveNum+1, rollMax, mem))%MOD;
                }
            }
            mem[leftRolls][tail][consectiveNum] = ans;
            return ans;
        }
    }





    public static void main(String[] args) {
        int[] nums = {1,1,2,2,2,3};
        Solution2 solution = new Solution2();
        System.out.println(solution.dieSimulator(3, new int[]{1,1,1,2,2,3}));


        int[] newnums = {10,2,8,9,3,8,1,5,2,3,7,6};
        Arrays.sort(newnums);
        Arrays.stream(newnums).forEach(t->System.out.print(t + " "));
    }
}
