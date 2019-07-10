package lt_500_599;

import java.util.HashMap;
import java.util.Map;

/**
 * [523] Continuous Subarray Sum
 *
 * Solution1: 使用前缀和的差值来寻找区间 time:O(n^2)
 * Solution2: 使用hashMap,思路类似525 time:O(n)
 */
public class LC_523 {
    /**
     * 这里的判断容易出错,需要考虑k为0的情况
     * @param val
     * @param k
     * @return
     */
    private boolean judge(int val, int k) {
        return (k==0&&val==0) || (k!=0 && val%k == 0);
    }
    /**
     * Solution1: 使用前缀和的差值来寻找区间 time:O(n^2)
     * k == 0 ?
     * k == 1 ?
     */
    public boolean checkSubarraySum1(int[] nums, int k) {
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i-1];
        }
        for (int i = 1; i < nums.length; ++i) {
            if (judge(nums[i], k)) {
                return true;
            }
            for (int j = 0; j < i - 1; ++j) {
                if (judge(nums[i] - nums[j], k)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Solution2: 使用hashMap,思路类似525 time:O(n)
     * 解法类似525
     * hashMap
     * We iterate through the input array exactly once, keeping track of the running sum mod k of the elements in the process.
     * If we find that a running sum value at index j has been previously seen before in some earlier index i in the array, then we know that the sub-array (i,j] contains a desired sum.
     * 迭代一次输入数组，跟踪前缀和对k取余的结果，如果发现前缀和的结果在之前索引处已经出现并且组成子数组长度>=2,那么符合条件
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            preSum += nums[i];
            if (k != 0) {
                preSum %= k;
            }
            // 找到之前出现的preSum，说先在这区间差值为nk
            if (map.containsKey(preSum) && (i-map.get(preSum)) > 1) {
                return true;
            }
            if (!map.containsKey(preSum)) {
                map.put(preSum, i);
            }
        }
        return false;
    }
}
