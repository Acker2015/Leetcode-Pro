package lt_300_399;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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
}
