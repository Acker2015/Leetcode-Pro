package lt_1100_1199;

/**
 * 1140. Stone Game II
 * 记忆化DP搜索
 * DP + dfs + memorization
 */
public class LC_1140 {
    static class Solition1 {
        /**
         * 每次选择比下一个人多多少（让每个人都去找到最优的解，那就是使得与下一个人的差达到最大),每一次都使得这个差值最大化
         *
         * A-B=x
         * A+B=sum
         * A = (x+sum)/2
         */
        int[][] mem;
        // 比下一个人多多少（让每个人都去找到最优的解，那就是使得与下一个人的差达到最大）
        private int dfs(int[] piles, int idx, int M) {
            if (idx >= piles.length) return 0;
            if (idx+2*M >= piles.length) {
                return piles[piles.length-1] - (idx>0 ? piles[idx-1]:0);
            }
            if (mem[idx][M]>0) {
                return mem[idx][M];
            }
            int max = Integer.MIN_VALUE;
            // max{sum(i,j) - dfs(j+1)}
            for (int i = idx; i < idx+2*M && i < piles.length; ++i) {
                int catchNum = piles[i] - (idx > 0 ? piles[idx-1] : 0);
                int more = dfs(piles, i+1, Math.max(M, i-idx+1));
                max = Math.max(max, catchNum - more);
            }
            mem[idx][M] = max;
            return max;
        }
        public int stoneGameII(int[] piles) {
            int len = piles.length;
            if (len <= 0) return 0;
            for (int i = 1; i < len; ++i) {
                piles[i] += piles[i-1];
            }
            // maximum value of M is 32, 2+4+8+16+32+64>100
            mem = new int[len][33];
            return (piles[len-1] + dfs(piles, 0, 1))/2;
        }
    }


    /**
     * solution2:
     * helper方法获取A可以得到的最多的石头， A如何选择？
     * 这是一个minmax的问题，取后一个人选择最大值的最小值即可，转化为 [A的选择使得下一个人选择能拿到的石头最小]
     */
    static class Solution2 {
        private int[] sums;//the sum from piles[i] to the end
        private int[][] hash;
        public int stoneGameII(int[] piles) {
            if(piles == null || piles.length == 0) return 0;
            int n = piles.length;
            sums = new int[n];
            sums[n-1] = piles[n-1];
            for(int i = n -2; i>=0;i--) {
                sums[i] = sums[i+1] + piles[i]; //the sum from piles[i] to the end
            }

            hash = new int[n][n];
            return helper(piles, 0, 1);
        }
        // helper means the most stones when A selects stones from index i with M.
        // sums[i] - min{helper(i+x, Math.max(M, x))} -> (1<=x<=2*M)
        private int helper(int[] a, int i, int M) {
            if(i == a.length) return 0;
            if(2*M >= a.length - i) {
                return sums[i];
            }
            if(hash[i][M] != 0) return hash[i][M];
            int min = Integer.MAX_VALUE;//the min value the next player can get
            for(int x=1;x<=2*M;x++){
                min = Math.min(min, helper(a, i+x, Math.max(M,x)));
            }
            hash[i][M] = sums[i] - min;  //max stones = all the left stones - the min stones next player can get
            return hash[i][M];
        }
    }

    public static void main(String ...args) {
        int[] piles = {2,7,9,4,4};
        System.out.println(new LC_1140.Solition1().stoneGameII(piles));
    }
}
