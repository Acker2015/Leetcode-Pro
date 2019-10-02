package lt_200_299;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * [215] Kth Largest Element in an Array
 *
 * solution1 简单排序
 * solution2 最大堆
 * solution3 最小堆
 * solution4 selection algorithm
 */
public class LC_215 {
    public static class Solution1 {
        /**
         * 最大堆解法
         * O(n)+O(klogn)
         * @param nums
         * @param k
         * @return
         */
        public int findKthLargest1(int[] nums, int k) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b-a);
            for (int num: nums) {
                priorityQueue.offer(num);
            }
            while (--k > 0) {
                priorityQueue.poll();
            }
            return priorityQueue.peek();
        }
    }

    public static class Solution2 {
        /**
         * 最小堆解法
         * O(k)+O((n-k)logk)
         * @param nums
         * @param k
         * @return
         */
        public int findKthLargest2(int[] nums, int k) {
            if (nums.length < k) return -1;
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int i = 0; i < nums.length; ++i) {
                if (priorityQueue.size() >= k) {
                    priorityQueue.poll();
                }
                priorityQueue.offer(nums[i]);
            }
            return priorityQueue.peek();
        }
    }


    /**
     * Solution3
     *
     * selection algorithm
     * partition
     * 选择算法 -类似快排
     * O(N)
     * 通过shuffle来防止最坏情况的发生(本来就有序)
     * @return
     */
    public static class Solution3 {
        private void swap(int[] nums, int x, int y) {
            int ans = nums[x];
            nums[x] = nums[y];
            nums[y] = ans;
        }
        // shuffle让nums中乱序，防止最坏情况的发生
        private void shuffle(int[] nums) {
            Random random = new Random();
            for (int i = 1; i < nums.length; ++i) {
                swap(nums, i, random.nextInt(i+1));
            }
        }
        private int partition(int[] nums, int left, int right, int k) {
            int tmp = nums[left];
            int l = left, r = right;
            while (l < r) {
                while (l < r && nums[r] > tmp) r--;
                nums[l] = nums[r];
                while (l < r && nums[l] <= tmp) l++;
                nums[r] = nums[l];
            }
            nums[l] = tmp;
            if (l - left + 1 == k) {
                return tmp;
            } else if (l-left+1 > k) {
                return partition(nums, left, l-1, k);
            } else {
                return partition(nums, l+1, right, k-(l-left+1));
            }
        }
        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            k = (k-1)%len + 1;
            k = len-k+1;
            shuffle(nums);
            return partition(nums, 0, len-1, k);
        }
    }




    public static void main(String ...args) {
        int[] nums = {3,2,1,5,6,4};
        Solution3 solution = new Solution3();
        System.out.println(solution.findKthLargest(nums, 3));
    }
}
