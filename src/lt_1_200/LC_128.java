package lt_1_200;


import java.util.HashMap;
import java.util.Map;

/**
 * [128] Longest Consecutive Sequence
 */
public class LC_128 {
    public static class Solution1 {
        /**
         * 类似并查集的思路
         *
         * 遇到新的num，查看num-1,num+1是否已经出现序列，那么num就连接两个序列，并更新整个序列内最大连续个数
         *
         * 优化点：
         *      更新连续个数的时候，只需要更新序列的两端就可以，中间的不需要更新，因为连接点只可能出现在两端
         * @param nums
         * @return
         */
        public int longestConsecutive1(int[] nums) {
            if (nums.length <= 0) return 0;
            Map<Integer, Integer> map = new HashMap<>();
            int maxCons = 1;
            for (int num: nums) {
                if (!map.containsKey(num)) { // 关键一步判断
                    int upper = map.getOrDefault(num+1, 0);
                    int lower = map.getOrDefault(num-1, 0);
                    int ans = 1 + upper + lower;
                    map.put(num, ans);
                    // 只更新两端
                    if (upper > 0) {
                        map.put(num+upper, ans);
                    }
                    if (lower > 0) {
                        map.put(num-lower, ans);
                    }
                    maxCons = Math.max(maxCons, ans);
                }
            }
            return maxCons;
        }
    }


    /**
     * 裸并查集 - 没有做边界长度处理 - O(N^2)
     */
    public static class Solution2 {
        private int findLen(Map<Integer, Integer> parentMap, int lower) {
            if (parentMap.get(lower).equals(lower)) {
                return 1;
            }
            return findLen(parentMap, parentMap.get(lower)) + 1;
        }
        public int longestConsecutive(int[] nums) {
            if (nums.length <= 1) return nums.length;
            Map<Integer, Integer> parentMap = new HashMap<>();
            for (int num: nums) {
                if (!parentMap.containsKey(num)) {
                    int upper = num+1;
                    int lower = num-1;
                    if (parentMap.containsKey(upper)) {
                        parentMap.put(num, upper);
                    } else {
                        parentMap.put(num, num);
                    }
                    if (parentMap.containsKey(lower)) {
                        parentMap.put(lower, num);
                    }

                }
            }
            int maxLen = 0;
            for (int num: nums) {
                maxLen = Math.max(maxLen, findLen(parentMap, num));
            }
            return maxLen;
        }
    }

    public static void main(String...args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.longestConsecutive(nums));
    }
}
