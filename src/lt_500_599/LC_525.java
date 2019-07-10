package lt_500_599;

import java.util.HashMap;
import java.util.Map;

/**
 * [525] Contiguous Array
 */
public class LC_525 {
    /**
     * [1,0,0,0,1,0,0,0,0,1,1]
     * 将0看作是-1，相当于求前缀和
     * 如果sum(j)-sum(i)=0, (i, j]之间的0与1个数相等
     * 那么只需要记录每一个preSum对应的索引位置即可
     */
    public int findMaxLength(int[] nums) {
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            preSum += nums[i] > 0 ? 1 : -1;
            if (!map.containsKey(preSum)) {
                map.put(preSum, i);
            } else {
                maxLen = Math.max(maxLen, i - map.get(preSum));
            }
        }
        return maxLen;
    }
}
