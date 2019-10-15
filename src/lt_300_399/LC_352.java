package lt_300_399;


import java.util.TreeMap;

/**
 * [352] Data Stream as Disjoint Intervals
 * solution1: 使用treeMap实现
 *              addNum: time(logn)
 *
 * solution2: 独立构建BST来解决，当然treeMap底层也是用的相对平衡的红黑树
 *
 */
public class LC_352 {
    /*
     * @lc app=leetcode id=352 lang=java
     *
     *
     */
    // @lc code=start
    static class SummaryRanges {
        private TreeMap<Integer, int[]> treeMap;

        /** Initialize your data structure here. */
        public SummaryRanges() {
            this.treeMap = new TreeMap<>();
        }

        /**
         * 1. 不加
         * 2. 独立增加
         * 3. 与lower或者higher合并
         * 4. 连接lower和higher
         * @param val
         */
        public void addNum(int val) {
            if (treeMap.containsKey(val)) return;
            Integer lowerKey = treeMap.lowerKey(val);
            Integer higherKey = treeMap.higherKey(val);
            if (lowerKey!=null && higherKey!=null && val==treeMap.get(lowerKey)[1]+1 && val==higherKey-1) {
                treeMap.get(lowerKey)[1] = treeMap.get(higherKey)[1];
                treeMap.remove(higherKey);
            } else if (lowerKey != null && val<=treeMap.get(lowerKey)[1]+1) {
                treeMap.get(lowerKey)[1] = Math.max(treeMap.get(lowerKey)[1], val);
            } else if (higherKey !=null && higherKey==val+1) {
                int[] interval = treeMap.get(higherKey);
                treeMap.remove(higherKey);
                interval[0] = val;
                treeMap.put(val, interval);
            } else {
                treeMap.put(val, new int[]{val, val});
            }
        }

        public int[][] getIntervals() {
            int[][] ret = new int[treeMap.size()][2];
            int i = 0;
            for (int[] interval : treeMap.values()) {
                ret[i++] = interval;
            }
            return ret;
        }

        public void printIntervals() {
            int[][] ret = getIntervals();
            for (int[] interval: ret) {
                System.out.print("["+interval[0]+","+interval[1]+"], ");
            }
            System.out.println();
        }
    }

    // 1, 3, 7, 2, 6
    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.printIntervals();
        summaryRanges.addNum(3);
        summaryRanges.printIntervals();
        summaryRanges.addNum(7);
        summaryRanges.printIntervals();
        summaryRanges.addNum(2);
        summaryRanges.printIntervals();
        summaryRanges.addNum(6);
        summaryRanges.printIntervals();
    }
}
