package lt_200_299;


import java.util.*;

/**
 * [279] Perfect Squares
 *
 * 1. BFS
 * 2. top-down dfs
 * 3. dp
 */
public class LC_279 {

    /**
     * BFS
     * @return
     */
    static class Solution1 {
        public int numSquares(int n) {
            int ans = (int)Math.sqrt(n);
            if (ans*ans == n) return 1;

            Set<Integer> set = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int i = ans; i >= 1; --i) {
                int left = n - i*i;
                queue.offer(left);
                set.add(left);
            }
            int number = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    int tmp = queue.poll();
                    for (int i = (int)Math.sqrt(tmp); i >= 1; --i) {
                        if (i*i == tmp) {
                            return number+1;
                        }
                        if (!set.contains(tmp-i*i)) {
                            queue.offer(tmp-i*i);
                        }
                    }
                }
                number++;
            }
            return n;
        }
    }

    /**
     * DP
     */
    static class Solution2 {
        private int divide(int n, Map<Integer, Integer> map) {
            if (n == 0) return 0;
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int step = Integer.MAX_VALUE;
            int ans = (int)Math.sqrt(n);
            for (int i = ans; i >= 1; --i) {
                step = Math.min(step, divide(n-i*i, map));
            }
            map.put(n, step+1);
            return step+1;
        }

        public int numSquares(int n) {
            return divide(n, new HashMap<>());
        }
    }

    /**
     * 简单DP
     *
     * O(N^2)
     */
    static class Solution3 {
        // dp[i] 表示i的min-number
        public int numSquares(int n) {
            int[] dp = new int[n+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; ++i) {
                for (int j = 1; j * j <= i; ++j) {
                    dp[i] = Math.min(dp[i], dp[i-j*j]+1);
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.numSquares(12));
    }
}
