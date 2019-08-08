package lt_500_599;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 * 思想类似L496，
 * 使用栈找出下一个更大的值(不过这里需要通过下标索引来记录)
 * 由于环的存在，这里再找更大值的时候，遍历两圈。
 * 
 * input  [5, 4, 3, 2, 1] 
 * output [-1,5, 5, 5, 5]
 * @author Acker
 *
 */
public class LC_503 {
	public int[] nextGreaterElements(int[] nums) {
		if (nums == null) return null;
		if (nums.length <= 0) return new int[0];
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> indexStack = new Stack<>();
		Map<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < nums.length * 2; i++) {
			int index = i % nums.length;
			if (stack.isEmpty() || stack.peek() >= nums[index]) {
				stack.push(nums[index]);
				indexStack.push(index);
			} else {
				while (!stack.isEmpty() && stack.peek() < nums[index]) {
					stack.pop();
					indexMap.put(indexStack.pop(), nums[index]);
				}
				stack.push(nums[index]);
				indexStack.push(index);
			}
		}
		int[] retArr = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			retArr[i] = indexMap.getOrDefault(i, -1);
		}
        return retArr;
    }
}
