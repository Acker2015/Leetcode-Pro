package lt_200_299;

import java.util.*;

public class LC_220 {// find first least val that is greater than val
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
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums.length <= 0 || k <= 0) return false;
        List<Long> list = new ArrayList<>(k+1);
        for (int i = 0; i < nums.length; ++i) {
            Collections.sort(list);
            Long left = binary_ceil(list, Long.valueOf(nums[i])-t);
            Long right = binary_floor(list, Long.valueOf(nums[i])+t);
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

    /**
     * Solution2
     * 太妙了！！！
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
            long bucket = remappedNum / ((long) t + 1);
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

    public static void main(String ...args) {
        LC_220 lc_220 = new LC_220();
        int[] nums = {4, 2, 7};
        System.out.println(lc_220.containsNearbyAlmostDuplicate(nums, 2, 3));
    }
}
