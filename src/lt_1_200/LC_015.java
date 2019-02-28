package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * three sum
 * 1. 排序
 * 2. 从头遍历，对于每一个数A，在余下的list中通过two pointers完成two sum
 * 3. 去重  	-> 对A的去重
 * 			-> two pointers计算时候的去重
 * The idea is to sort an input array and then run through all indices of a possible first element of a triplet. 
 * For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array. 
 * Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
 * @author Acker
 *
 */
public class LC_015 {
	public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 0) return null;
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; ++i) {
        		if (i > 0 && nums[i]==nums[i-1]) continue;
        		int l = i + 1, r = nums.length - 1;
        		while(l < r) {
        			if (nums[l] + nums[r] > -nums[i]) {
        				r--;
        			} else if(nums[l] + nums[r] < -nums[i]) {
        				l++;
        			} else {
        				result.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
        				while(l < r && nums[l]==nums[l-1]) l++;
        				while(l < r && nums[r]==nums[r+1]) r--;
        			}
        		}
        }
        return result;
    }
	public static void main(String[] args) {
		int[] nums = {-1, 0, 1, 2, -1, -4};
		LC_015 lc_015 = new LC_015();
		List<List<Integer>> list = lc_015.threeSum(nums);
		System.out.println(list.toString());
	}

}
