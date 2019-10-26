package lt_200_299;


import java.util.*;

public class LC_253_lock {

    public static class Solution1 {
        /**
         * 会议按照开始时间排序
         *
         * 优先队列（最小堆）记录各个会议室的结束时间
         * @param intervals
         * @return
         */
        public int minMeetingRooms(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));   // 按照起始时间排序
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < intervals.length; ++i) {
                if (pq.isEmpty()) {
                    pq.offer(intervals[i][1]);
                } else {
                    if (intervals[i][0] >= pq.peek()) {
                        pq.poll();
                    }
                    pq.offer(intervals[i][1]);
                }
            }
            return pq.size();
        }
    }

    /**
     * 对会议按照开始时间排序
     * 利用treeset存储之前会议室的结束时间
     *
     * 遇到每一个会议interval，在treeset中找结束时间小于等于当前会议开始时间的会议室
     */
    public static class Solution2 {
        // O(nlogn)
        public int minMeetingRooms(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
            TreeSet<Integer> treeSet = new TreeSet<>();
            for (int[] interval: intervals) {
                Integer end = treeSet.floor(interval[0]);
                if (end != null) {
                    treeSet.remove(end);
                }
                treeSet.add(interval[1]);
            }
            return treeSet.size();
        }
    }


    public static void main(String[] args) {
        int[][] intervals = {{0, 30},{5, 10},{15, 20}};

        Solution1 solution = new Solution1();
        System.out.println(solution.minMeetingRooms(intervals));

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.minMeetingRooms(intervals));
    }
}
