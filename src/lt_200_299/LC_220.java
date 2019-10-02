package lt_200_299;

import java.util.*;

/**
 * [220] Contains Duplicate III
 * 1. 二分找上下界
 * 2. 根据宽度设置桶的大小，桶排序思路找是否有符合条件的值
 *
 */
public class LC_220 {
    // solution1

    /**
     * 手写二分
     */
    public static class Solution1 {
        // find first least val that is greater than val
        private Long binary_ceil(List<Long> nums, Long val) {
            int l = 0, r = nums.size(), mid;
            while (l < r) {
                mid = l + (r - l) / 2;
                if (nums.get(mid) < val) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l == nums.size() ? null : nums.get(l);
        }
        // find first greatest val that is less than or equals to val
        private Long binary_floor(List<Long> nums, Long val) {
            int l = 0, r = nums.size(), mid;
            while (l < r) {
                mid = l + (r - l) / 2;
                if (nums.get(mid) <= val) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l == 0 ? null : nums.get(l-1);
        }
        // search the position of val
        private int binary_search(List<Long> nums, Long val) {
            int l = 0, r = nums.size()-1, mid;
            while (l <= r) {
                mid = l + (r - l)/2;
                if (nums.get(mid).equals(val)) {
                    return mid;
                } else if (nums.get(mid) < val) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return -1;
        }
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length <= 0 || k <= 0) return false;
            List<Long> list = new ArrayList<>(k+1);
            for (int i = 0; i < nums.length; ++i) {
                Collections.sort(list);
                Long left = binary_ceil(list, (long)nums[i]-t);
                Long right = binary_floor(list, (long)nums[i]+t);
                if (left != null && left <= nums[i] || right != null && right >= nums[i]) {
                    return true;
                }
                if (list.size() == k) {
                    int idx = binary_search(list, (long)nums[i-k]);
                    list.set(idx, (long)nums[i]);
                } else {
                    list.add((long)nums[i]);
                }
            }
            return false;
        }
    }


    public static class Solution2 {
        /**
         * Solution2
         * treeset中保持k个元素
         * 每次遇到新元素通过二分查找大于等于(nums[i]-t)的值，以及小于等于(nums[i]+t)的值
         *
         * @param nums
         * @param k
         * @param t
         * @return
         */
        public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
            if (nums.length <= 1) return false;
            TreeSet<Long> treeSet = new TreeSet<>();
            // 防止k=0， 这里不要先往treeset中添加元素
            for (int i = 0; i < nums.length; ++i) {
                Long left = treeSet.ceiling((long)nums[i] - t);
                Long right = treeSet.floor((long)nums[i] + t);
                //System.out.println(right);
                if ((left!=null && left<=nums[i]) || (right!=null && right >= nums[i])) {
                    return true;
                }
                treeSet.add((long)nums[i]);
                if (treeSet.size() > k) {
                    treeSet.remove((long)nums[i-k]);
                }
            }
            return false;
        }
    }

    public static class Solution3 {
        /**
         * Solution3
         * 桶排序
         * 桶的大小为(t+1), 这样出现在桶中的元素肯定都是符合|a-b| <= t的
         * 所以只需要检查本桶有没有元素 or 前后两个桶的元素有没有符合要求的
         *
         * @param nums
         * @param k
         * @param t
         * @return
         */
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (k < 1 || t < 0) return false;
            Map<Long, Long> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
                long bucket = remappedNum / ((long) t + 1); // 每个桶里有t+1个数，保证桶内元素相差在t之内
                if (map.containsKey(bucket)
                        || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                        || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                    return true;
                if (map.entrySet().size() >= k) {
                    long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                    map.remove(lastBucket);
                }
                // 直接将bucket覆盖，这里不会出现覆盖的情况(不考虑map元素数量大于k的情况)，因为同一个桶出现在上边的检查已经可以返回true了
                map.put(bucket, remappedNum);
            }
            return false;
        }
    }


    public static void main(String ...args) {
        int[] nums = {4, 2, 7};
        Solution3 solution = new Solution3();
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, 2, 3));
    }
}
