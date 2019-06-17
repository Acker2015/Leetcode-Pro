package lt_300_399;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [368] Largest Divisible Subset
 */
public class LC_368 {
    /**
     * 类似最大上升子序列的DP方法
     * dp[i]表示包括i的largest divisible subset
     * 一开始我直接记录每个位置的subset的链表，导致每次更新都需要addAll
     * improve：
     *      可以设置一个前驱数组和一个计数数组来替代
     *
     * time: O(n^2)
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> retList = new ArrayList<>();
        int n = nums.length;
        if (n <= 0) {
            return retList;
        }
        Arrays.sort(nums);
        int[] pre = new int[n];
        int[] count = new int[n];
        int retLoc = 0;
        for (int i = 0; i < n; ++i) {
            pre[i]=-1;
            count[i]=1;
            for (int j = 0; j < i; ++j) {
                if (nums[i]%nums[j] == 0 && count[j]+1 > count[i]) {
                    count[i] = count[j]+1;
                    pre[i] = j;
                }
            }
            if (count[retLoc] < count[i]) {
                retLoc = i;
            }
        }
        while (retLoc>-1) {
            retList.add(nums[retLoc]);
            retLoc = pre[retLoc];
        }
        return retList;
    }

    public static void main(String ...args) {
        LC_368 lc_368 = new LC_368();
        int[] nums = {2, 3, 4, 6, 9, 27};
        List<Integer> list = lc_368.largestDivisibleSubset(nums);
        list.forEach(System.out::println);
    }
}
