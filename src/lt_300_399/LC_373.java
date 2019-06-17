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
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> list = new ArrayList<>();
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 <= 0 || len2 <= 0) return list;
        //int[] indexFlag = new int[len1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0]+a[1]-b[0]-b[1]);
        for (int i = 0; i < len1 && i < k; ++i) {
            queue.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !queue.isEmpty()) {
            int[] top = queue.poll();
            list.add(Arrays.asList(top[0], top[1]));
            if (top[2]+1 < len2) {
                queue.offer(new int[]{top[0], nums2[top[2]+1], top[2]+1});
            }
        }
        return list;
    }
}
