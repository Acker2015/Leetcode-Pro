package lt_400_499;


import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LC_436 {
    /**
     * @lc app=leetcode id=436 lang=java
     *
     * [436] Find Right Interval
     *
     * 解法一： 排序+二分
     * 解法二：TreeMap二分，借助treeMap的ceilingEntry方法
     */
    class Solution {
        private int binarySearch(int[][] subs, int val) {
            int left = 0, right = subs.length;
            while (left < right) {
                int mid = left+(right-left)/2;
                if (subs[mid][0] < val) {
                    left = mid+1;
                } else {
                    right = mid;
                }
            }
            return left < subs.length ? subs[left][2] : -1;
        }
        /**
         * 解法一：
         * 排序 - 二分
         * intuitive
         */
        public int[] findRightInterval(int[][] intervals) {
            int len = intervals.length;
            int[][] subs = new int[len][3];
            for (int i = 0; i < intervals.length; ++i) {
                subs[i] = new int[]{intervals[i][0], intervals[i][1], i};
            }
            Arrays.sort(subs, (a, b) -> a[0]-b[0]);
            int[] ret = new int[len];
            for (int i = 0; i < len; ++i) {
                int idx = binarySearch(subs, intervals[i][1]);
                ret[i] = idx;
            }
            return ret;
        }

        /**
         * 解法二
         * 使用TreeMap的ceilingEntry或者ceilingKey来二分
         */
        public int[] findRightInterval2(int[][] intervals) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < intervals.length; ++i) {
                treeMap.put(intervals[i][0], i);
            }
            int len = intervals.length;
            int[] ret = new int[len];
            for (int i = 0; i < len; ++i) {
                //Integer idx = treeMap.ceilingKey(intervals[i][1]);
                Map.Entry<Integer, Integer> entry = treeMap.ceilingEntry(intervals[i][1]);
                ret[i] = entry==null ? -1 : entry.getValue();
            }
            return ret;
        }
    }
}
