package lt_200_299;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * [239] Sliding Window Maximum
 *
 * [1,3,-1,-3,5,3,6,7], k = 3
 * 1
 * 1 3
 * -1 1 3
 * -3 -1 1 3
 * -> del
 * 5
 * 3 5
 * 6
 * 7
 *
 * [3,3,5,5,6,7]
 *
 * 维护一个双端队列 或者 一个列表
 * 数据变动过程中保证两点
 *  1. 数据有序（如从左到右升序）
 *  2. 窗口外的老数据不干扰窗口最值计算，即在合适的时机能把老数据移除
 *
 * 算法流程：
 * 保证数据从左到右升序
 *  1. 从右侧往左遍历，将过期的节点删除(这样可以防止过期的值影响窗口最大值的判断)
 *  2. 新数据val在左边插入，从链表左侧不断移除比val小的点。（由于val是最新的，所以移除老数据没有问题）
 *
 */
public class LC_239 {
    /**
     * 使用链表模拟Dequeue
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] maxArr = new int[len-k+1];
        List<Integer> memoryQueue = new LinkedList<>();
        int idx = 0;
        for (int i = 0; i < len; ++i) {
            // delete out-date node in right
            while (idx > 0 && memoryQueue.get(idx-1) <= i-k) {
                memoryQueue.remove(--idx);
            }
            // delete the node which is less then nums[i] from left, this can make sure the list is sortable in [from to)
            while (idx > 0 && nums[memoryQueue.get(0)] <= nums[i]) {
                memoryQueue.remove(0);
                idx--;
            }
            memoryQueue.add(0, i);
            idx++;
            if (i >= k - 1) {
                maxArr[i-k+1] = nums[memoryQueue.get(idx-1)];
            }
        }
        return maxArr;
    }
    public static void main(String...args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        LC_239 lc_239 = new LC_239();
        Arrays.stream(lc_239.maxSlidingWindow(nums, 3)).forEach(System.out::println);
    }
}
