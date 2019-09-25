package lt_300_399;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [354-Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/description/)
 *
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you Russian doll? (put one inside other)

 Note:
 Rotation is not allowed.

 * 根据宽度从小到达排序，然后就可以求对应高度的最大上升子序列来求解可以嵌套的最大信封的数量
 * 问题点：排序的时候如果宽度相同的情况下，高度应该如何排序？
 *        显然，求高度的上升序列的时候，相同宽度的不能互相嵌套。所以这里宽度相同的时候，高度按照高低顺序排，这样就能保证宽度相同的不会嵌套
 *
 * solution1: 直接排序之后使用裸的求最大上升子序列的解法，time: O(N^2)
 * solution2: 最大上升子序列的时间优化，time: O(NlogN)
 *
 */
public class LC_354 {

    /**
     * 二分优化最大上升子序列 O(nlogn)
     */
    public static class Solution1 {
        // find first index with it's val equals with or greater than val in [l, r]
        private int binary_search(List<Integer> dpList, int val) {
            int l = 0, r = dpList.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (dpList.get(mid) < val) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, (a,b) -> a[0]!=b[0] ? a[0]-b[0] : b[1]-a[1]);
            int len = envelopes.length;
            if (len <= 1) return len;
            List<Integer> dpList = new ArrayList<>();
            dpList.add(envelopes[0][1]);
            for (int i = 1; i < envelopes.length; ++i) {
                int idx = binary_search(dpList, envelopes[i][1]);
                if (idx < dpList.size()) {
                    dpList.set(idx, envelopes[i][1]);
                } else {
                    dpList.add(envelopes[i][1]);
                }
            }
            return dpList.size();
        }
    }

    /**
     * 裸DP
     *
     * time O(n^2)
     */
    public static class Solution2 {
        // dp[i] = Math.max{dp[i], dp[j] + 1} when envelopes[i] can contain envelopes[j]
        public int maxEnvelopes1(int[][] envelopes) {
            // 注意这里的排序规则，如果宽度相同，需要把高度高的排在前边，这样可以防止宽度相同的信封envelope塞入
            Arrays.sort(envelopes, (a, b) -> a[0]!=b[0] ? a[0]-b[0] : b[1]-a[1]);
            int len = envelopes.length;
            if (len <= 1) return len;
            int[] dp = new int[len];
            dp[0] = 1;
            int maxE = 1;
            for (int i = 1; i < len; ++i) {
                dp[i] = 1;
                for (int j = 0; j < i; ++j) {
                    // 因为envelopes[i]的宽>=envelopes[j]的宽, 宽度相同的高的排序在前边，所以只需要根据高度的包含关系来判断是否包含即可
                    if (envelopes[i][1] > envelopes[j][1]) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
                maxE = Math.max(maxE, dp[i]);
            }
            return maxE;
        }
    }


}
