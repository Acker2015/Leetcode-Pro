package lt_500_599;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * [532] K-diff Pairs in an Array
 * sort+hash+two-pointers
 *
 * 两个指针i和j,从头开始遍历
 * 如果nums[j]-nums[i] < k, 那么需要扩大差值,j++
 * 如果nums[j]-nums[i] > k, 那么需要缩小差值,i++
 * 如果nums[j]-nums[i] == k， 那么判断pair是否重复并更改结果，j++
 */
public class LC_532 {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, j = 1, cnt = 0;
        Set<Integer> set = new HashSet<>();
        while (j < nums.length) {
            if (i==j) {
                j++;
            } else if (nums[j]-nums[i] == k) {
                if (!set.contains(nums[j])){
                    cnt++;
                    set.add(nums[j]);
                }
                j++;
            } else if (nums[j]-nums[i] < k) {
                j++;
            } else {
                i++;
            }
        }
        return cnt;
    }
}
