package lt_300_399;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [377] Combination Sum IV
 * solution1: DFS
 * solution2: DP
 */
public class LC_377 {
    public int combine(int[] nums, int target, Map<Integer, Integer> hash) {
        if (target == 0) {
            return 1;
        }
        if (hash.containsKey(target)) {
            return hash.get(target);
        }
        int res = 0;
        for (int num: nums) {
            if (target >= num) {
                res += combine(nums, target-num, hash);
            }
        }
        hash.put(target, res);
        return res;
    }
    /**
     * Solution1
     * 使用DFS
     * 可以使用map进行优化
     */
    public int combinationSum4_0(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        return combine(nums, target, map);
    }


    /**
     * Solution2
     * DP
     * dp[i]表示nums中的数组成和为i的组合个数
     *
     * 遍历nums中的每一个num
     * num==i -> dp[i] += 1
     * num>i -> dp[i] += dp[i-num];
     */
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] res = new int[target + 1];
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (num > i) break;
                else if (num == i)
                    res[i] += 1;
                else
                    res[i] += res[i-num];
            }
        }
        return res[target];
    }
}
