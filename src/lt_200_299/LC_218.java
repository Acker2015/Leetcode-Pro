package lt_200_299;

import java.util.*;

/**
 * [218] The Skyline Problem
 * https://leetcode.com/problems/the-skyline-problem/description/
 *
 * https://www.zhihu.com/people/wang-xu-55-63/activities
 * https://briangordon.github.io/2014/08/the-skyline-problem.html
 * https://www.youtube.com/watch?v=GSBLe8cKu0s （youtube视频）
 *
 * 举例，[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]
 * 我们需要转换成:[[2, 10, 's'], [3, 15, 's'], [5, 12, 's'], [7, 15, 'e'], [9, 10, 'e'], [12, 12, 'e'], [15, 10, 's'], [19, 8, 's'], [20, 10, 'e'], [24, 8, 'e']]
 * 其中，以s为结尾的代表起始点坐标，以e为结尾的代表结束点坐标。上面的转换函数不难写，
 * 但是，如果你看不懂这是什么意思，请先思考清楚而不是继续往下看下去。
 * 与此同时，我们需要一种数据结构，这种数据结构可以帮我们记录当前坐标 x 对应的最大楼高是多少.
 * 因此，我们希望这种数据结构有自动排序的功能，当然，我们可以自己实现这样的数据结构，但是leetcode已经为我们做好了接口。这种数据结构正是有名的priority queue。
 *
 *
 *
 * 1. 如果有楼以相同的坐标起始，或以相同的坐标结束，我们该怎么处理？
 *  --  相同坐标起始，那就将对应高度全部入优先队列
 *       相同坐标结束，那么就将对应高度全部出优先队列
 *       相同坐标起始或者结束，同样一起进出
 * 2. 我们为什么要使用priority queue?
 *  --  记录遍历过程中的最大高度，便于跟踪高度的变化
 * 3. 这算法的时间复杂度是多少?为什么？
 *  --  O(nlogn) 楼层结束高度删除调整需要logn的时间
 * 4. 为什么一有最大高度变化就输出结果？
 *  --  因为高度变化说明出现新的拐角
 */
public class LC_218 {
    private class CriticalPoint implements Comparable<CriticalPoint> {
        int x;
        int y;
        boolean isBeign;
        CriticalPoint(int x, int y, boolean isBegin) {
            this.x = x;
            this.y = y;
            this.isBeign = isBegin;
        }
        @Override
        public int compareTo(CriticalPoint cp) {
            return this.x - cp.x;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> retList = new ArrayList<>();
        if (buildings.length <= 0) return retList;
        List<CriticalPoint> cpList = new ArrayList<>();
        // 标识起始坐标
        for (int[] bd: buildings) {
            cpList.add(new CriticalPoint(bd[0], bd[2], true));
            cpList.add(new CriticalPoint(bd[1], bd[2], false));
        }
        Collections.sort(cpList);
        // 使用优先队列,记录building的高度，最大高度在前，遇到building的右端点，就将高度输出
        PriorityQueue<Integer> heightPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> -a));
        heightPriorityQueue.offer(0);
        int i = 0;
        while (i < cpList.size()) {
            int originMaxY = heightPriorityQueue.peek();
            int j = i;
            int nowX = cpList.get(i).x;
            while (j < cpList.size() && cpList.get(j).x == nowX) {
                if (cpList.get(j).isBeign) {
                    heightPriorityQueue.add(cpList.get(j).y);
                } else {
                    heightPriorityQueue.remove(cpList.get(j).y);
                }
                j++;
            }
            // max height has changed.
            if (originMaxY != heightPriorityQueue.peek()) {
                List<Integer> ans = new ArrayList<>();
                ans.add(nowX);
                ans.add(heightPriorityQueue.peek());
                retList.add(ans);
            }
            i = j;
        }
        return retList;
    }
    public static void main(String ...args) {
        //int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] buildings = {{0,2,3}, {2,5,3}};
        LC_218 lc_218 = new LC_218();
        List<List<Integer>> list = lc_218.getSkyline(buildings);
        list.forEach(p -> System.out.println("[" +p.get(0) + "," + p.get(1) + "]"));
    }
}
