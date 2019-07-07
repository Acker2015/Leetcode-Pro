package lt_300_399;

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
    private class Tuple implements Comparable<Tuple> {
        int row, column;
        int val;
        public Tuple(int row, int column, int val) {
            this.row = row;
            this.column = column;
            this.val = val;
        }
        @Override
        public int compareTo(Tuple t) {
            return this.val - t.val;
        }
    }

    /**
     * 优先队列（最小堆）
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> queue = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            queue.offer(new Tuple(i, 0, matrix[i][0]));
        }
        while (--k > 0) {
            Tuple tuple = queue.poll();
            if (tuple.column+1 < n) {
                queue.offer(new Tuple(tuple.row, tuple.column+1, matrix[tuple.row][tuple.column+1]));
            }
        }
        return queue.peek().val;
    }

    /**
     * 二分法-二分值
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n-1][n-1];
        while (left < right) {
            int mid = left + (right - left) / 2;
            //System.out.println(mid);
            int j = n - 1;
            int cnt = 0;    // num of value in the matrix that is less than or equals to mid;
            for (int i = 0; i < n; ++i) {
                while (j >= 0 && matrix[i][j] > mid) j--;
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
