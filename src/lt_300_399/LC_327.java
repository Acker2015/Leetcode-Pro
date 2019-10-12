package lt_300_399;

import java.util.Arrays;

/**
 * [327] Count of Range Sum
 * hard
 */
public class LC_327 {
    private static class Node {
        Node left, right;
        long val;
        int repeat; // 节点值重复次数
        public Node(long val) {this.val=val; this.repeat = 1;}
    }

    /**
     * 利用BST进行范围查找，缩短查找时间
     * 通过前缀和，从后往前将加入BST，查找preSum[i]-preSum[j]在[lower, upper]范围的节点个数
     * 遍历节点为j，相当于在BST中查找范围在[lower+preSum[j], upper+preSum[j]]之间的节点
     *
     * 相当于分别以索引 (len-1), (len-2), .... (0) 为起点找到合法区间
     * 复杂度 O(nlogn)
     */
    static class Solution1 {
        private void insert(Node parent, Node root, long val) {
            if (root == null) {
                Node node = new Node(val);
                if (val >= parent.val) {
                    parent.right = node;
                } else {
                    parent.left = node;
                }
                return;
            }
            if (root.val == val) {
                root.repeat++;
            } else if (root.val < val) {
                insert(root, root.right, val);
            } else {
                insert(root, root.left, val);
            }
        }
        private int query(Node node, long lower, long upper) {
            if (node == null || lower > upper) return 0;
            if (node.val < lower) {
                return query(node.right, lower, upper);
            } else if (node.val > upper) {
                return query(node.left, lower, upper);
            } else {
                return query(node.left, lower, node.val-1) + query(node.right, node.val+1, upper) + node.repeat;
            }
        }
        public int countRangeSum(int[] nums, int lower, int upper) {
            if (nums.length <= 0) return 0;
            long[] preSum = new long[nums.length+1];
            for (int i = 1; i <= nums.length; ++i) {
                preSum[i] = preSum[i-1]+nums[i-1];
            }
            int len = preSum.length;
            int ans = 0;
            Node root = new Node(Long.MIN_VALUE);
            insert(root, root.right, preSum[len-1]);
            for (int i = len-2; i >= 0; --i) {
                long n_low = lower+preSum[i];
                long n_upper = upper+preSum[i];
                ans += query(root.right, n_low, n_upper);
                if (i > 0) {
                    insert(root, root.right, preSum[i]);
                }
            }
            return ans;
        }
    }


    /**
     * merge sort
     * divide and conquer
     *
     * O(nlogn)
     *
     * https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution
     */
    static class Solution2 {
        public int countRangeSum(int[] nums, int lower, int upper) {
            if (nums.length <= 0) return 0;
            long[] presums = new long[nums.length+1];
            for (int i = 1; i < presums.length; ++i) {
                presums[i] = presums[i-1]+nums[i-1];
            }
            return mergeSort(presums, lower, upper, 0, presums.length-1);
        }

        // [left, right]
        /**
         * merge sort寻找逆序对的思路
         *
         * 如果数组[left, mid]以及[mid+1, right]分别已经有序，此时[mid+1, right]里边元素的原索引肯定都是大于[left, mid]中原索引的(merge sort性质)
         * 所以针对[left,mid]中的每一个元素k， 在[mid+1, right]中寻找一个满足 lower <= nums[x]-nums[k] <= upper
         * 这里只需要确定边界即可
         *      1. i >= mid+1 && nums[i]-nums[k] 是大于等于lower的最小值
         *      2. j > mid+1 && nums[j]-nums[k] 是大于upper的最小值
         * 那么可以确定在k处符合区间组合的个数为(j-i)
         *
         * 那么之后k往后遍历k+1, 由于nums[k+1]>=nums[k], 所以新的i和新的j可以在原来基础上继续寻找
         */
        private int mergeSort(long[] nums, int lower, int upper, int left, int right) {
            if (left >= right) {
                return 0;
            }
            int mid = left + (right-left)/2;
            int count = mergeSort(nums, lower, upper, left, mid) + mergeSort(nums, lower, upper, mid+1, right);
            int i = mid+1, j = mid+1;
            for (int k = left; k <= mid; ++k) {
                while (i <= right && nums[i]-nums[k] < lower) i++;
                while (j <= right && nums[j]-nums[k] <= upper) j++;
                count += (j-i);
            }
            Arrays.sort(nums, left, right+1);
            return count;
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String ...args) {
        int[] nums = {-2147483647,0,-2147483647,2147483647};
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        System.out.println(solution1.countRangeSum(nums, -564, 3864));
        System.out.println(solution2.countRangeSum(nums, -564, 3864));
    }
}
