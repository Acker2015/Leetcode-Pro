package lt_1_100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * 4sum
 * 原理类似于3sum
 * 注意这里有三处防止重复序列出现的代码约束
 * @author Acker
 *
 */
public class LC_018 {
	
	public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> retList = new LinkedList<>();
        if (nums.length < 4) return retList;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-3; i++) {
        		// 防止重复1
            if (i > 0 && nums[i-1]==nums[i]) continue;
            for (int j = i+1; j < nums.length-2; j++) {
            		// 防止重复2
                if (j > i+1 && nums[j]==nums[j-1]) continue;
                int left = j+1, right = nums.length-1;
                while(left < right) {
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum == target) {
                        retList.add(Arrays.asList(nums[i], nums[j], nums[left++], nums[right--]));
                        // notice 防止重复3，这里容易忽略
                        while(left<right&&nums[left-1]==nums[left]) left++;
                        while(left<right&&nums[right]==nums[right+1]) right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return retList;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
