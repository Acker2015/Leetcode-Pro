package lt_300_399;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 *
 * solution1: 优先队列（最小堆）
 * solution2: 值的二分
 */
public class LC_378 {

    public static class Solution1 {
        /**
         * 二分法-二分值
         */
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0], right = matrix[n-1][n-1];
            while (left < right) {
                int mid = left + (right - left) / 2;
                int j = n - 1;
                int cnt = 0;    // num of value in the matrix that is less than or equals to mid;
                for (int i = 0; i < n; ++i) {
                    while (j >= 0 && matrix[i][j] > mid) {
                        j--;
                    }
                    cnt += j+1;
                }
                if (cnt < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    /**
     * 优先队列（最小堆）
     */
    public static class Solution2 {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));
            for (int i = 0; i < n; ++i) {
                priorityQueue.offer(new int[]{0, i, matrix[0][i]});
            }
            int ret = 0;
            while (k-- > 0) {
                int[] cur = priorityQueue.poll();
                ret = cur[2];
                if (cur[0] < n-1) {
                    cur[0]+=1;
                    cur[2] = matrix[cur[0]][cur[1]];
                    priorityQueue.offer(cur);
                }
            }
            return ret;
        }
    }
}
