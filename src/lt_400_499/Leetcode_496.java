package lt_400_499;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-i/
 * 使用stack找出元素右边第一个比它大的元素
 * 遍历数组
 * 1.如果栈空或者栈顶元素大于遍历值，遍历值压栈
 * 2.如果栈顶元素小于遍历值，那么一直出栈直到栈空或者栈顶大于遍历值(出栈元素右边第一个比它大的值就是此遍历值)
 * @author Acker
 *
 */
public class Leetcode_496 {
	private Map<Integer, Integer> getRightGreaterIndexMap(int [] nums) {
		Map<Integer, Integer> indexMap = new HashMap<>();
		if (nums == null || nums.length <= 0) return indexMap;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			if (stack.isEmpty() || stack.peek() > nums[i]) {
				stack.push(nums[i]);
			} else {
				while(!stack.isEmpty() && stack.peek() < nums[i]) {
					indexMap.put(stack.pop(), nums[i]);
				}
				stack.push(nums[i]);
			}
		}
		return indexMap;
	}
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> indexMap = getRightGreaterIndexMap(nums2);
		if (nums1 == null) return null;
		if (nums1.length <= 0) return new int[0];
		int[] retArray = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			retArray[i] = indexMap.getOrDefault(nums1[i], -1);
		}
		return retArray;
    }
}
