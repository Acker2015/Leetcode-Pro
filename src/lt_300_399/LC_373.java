package lt_300_399;


import java.util.*;

/**
 * [373] Find K Pairs with Smallest Sums
 */
public class LC_373 {
    /**
     * nums1的每一个数都从nums2的第0个数配对开始
     * 使用优先队列每次找到最优对，
     * 然后更新最优对的nums2索引，
     * 加入到优先队列，重复以上过程
     *
     time O(KlogK)
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> list = new ArrayList<>(k);
        if (nums1.length<=0 || nums2.length<=0) return list;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> nums1[a[0]]+nums2[a[1]]-nums1[b[0]]-nums2[b[1]]);
        for (int i = 0; i < nums1.length && i < k; ++i) {
            queue.add(new int[]{i, 0});
        }
        while (k-- > 0 && !queue.isEmpty()) {
            int[] cur = queue.poll();
            list.add(Arrays.asList(nums1[cur[0]], nums2[cur[1]]));
            if (cur[1] < nums2.length-1) {
                cur[1] += 1;
                queue.offer(cur);
            }
        }
        return list;
    }

    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> list = new ArrayList<>(k);
        if (nums1.length <= 0 || nums2.length <= 0 || k <= 0) return list;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a->a[0]+a[1]));
        for (int i =0 ; i < k && i < nums1.length; ++i) {
            priorityQueue.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !priorityQueue.isEmpty()) {
            int[] cur = priorityQueue.poll();
            list.add(Arrays.asList(cur[0], cur[1]));
            if (cur[2] < nums2.length-1) {
                cur[1] = nums2[cur[2]+1];
                cur[2] += 1;
                priorityQueue.offer(cur);
            }
        }
        return list;
    }

    /**
     * [1,7,11]
     * [2,4,6]
     * 3
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        LC_373 lc_373 = new LC_373();
        lc_373.kSmallestPairs1(nums1, nums2, 3).forEach(list -> System.out.println(list.get(0)+", "+list.get(1)));
    }
}
