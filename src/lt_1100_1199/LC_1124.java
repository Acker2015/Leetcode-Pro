package lt_1100_1199;

import java.util.HashMap;
import java.util.Map;

/**
 * 1124. Longest Well-Performing Interval
 */
public class LC_1124 {
    /**
     * 归一到 -1 和 1
     * 1表示工作大于8小时
     * -1表示工作小于等于8小时
     * 求子数组和大于0的最大长度
     *
     * 通过前缀和，使用map来找到最早的preSum-1出现的节点
     *
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        int preSum = 0;
        for (int i = 0; i < hours.length; ++i) {
            preSum +=  hours[i] > 8 ? 1 : -1;
            if (preSum > 0) {
                maxLen = i+1;
            }
            map.putIfAbsent(preSum, i);
            if (map.containsKey(preSum-1)) {
                maxLen = Math.max(maxLen, i-map.get(preSum-1));
            }
        }
        return maxLen;
    }
}
