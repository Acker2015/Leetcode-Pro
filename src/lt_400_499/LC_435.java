package lt_400_499;

import java.util.Arrays;

public class LC_435 {
    /**
     * @lc app=leetcode id=435 lang=java
     *
     * [435] Non-overlapping Intervals
     * 排序 贪心
     */
    class Solution {
        /**
         * 按照起始点进行排序
         * greedy策略
         * 双指针，compare后一个interval的start与前一个interval的end，比较是否overlap
         * 如果overlap优先删掉end点较高的一个(这样可以降低与后续节点overlap的概率)
         *
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            int eraseNum = 0;
            Arrays.sort(intervals, (a, b) -> a[0]!=b[0] ? a[0]-b[0] : (a[1]-b[1]));
            int lastIdx = 0;
            for (int i = 1; i < intervals.length; ++i) {
                if (intervals[i][0] < intervals[lastIdx][1]) {
                    eraseNum++;
                    if (intervals[lastIdx][1] > intervals[i][1]) {
                        lastIdx = i;
                    }
                } else {
                    lastIdx = i;
                }
            }
            return eraseNum;
        }
    }
}
