package lt500_600;

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/submissions/
 * 题意：要求按照权重挑选索引。比如[1,99]中，有1%的概率挑选到索引0，有99%的概率挑选到索引1.
 * 概率分布 -> 累计概率分布
 * 比如输入[1,2,3,4]，那么概率分布是[1/10, 2/10, 3/10, 4/10]， 那么累计概率分布为[1/10, 3/10, 6/10, 10/10]，所以随机产生一个1-10之间的数，判断数字在哪个区间就可以了
 * [1, 2, 3, 4]，算出来的preSum为[1, 3, 6, 10]，然后看随机生成的1-10之间的数字出现在哪个区间
 * 
 *  查找区间使用二分法
 * 
 * @author Acker
 *
 */
public class Leetcode_528 {
	public static class Solution {
		int len;
		int[] preSum;
		Random random = new Random();
		
		public Solution(int[] w) {
			len = w.length;
	        preSum = new int[len];
	        preSum[0] = w[0];
	        for (int i = 1; i < len; ++i) {
	        		preSum[i] = preSum[i-1] + w[i];
	        }
	    }
		
		public int pickIndex() {
	        int rand = random.nextInt(preSum[len - 1]) + 1;
	        int left = 0, right = len - 1;
	        while (left + 1 < right) {
	        		int mid = (left + right) / 2;
	        		if (preSum[mid] == rand) {
	        			return mid;
	        		} else if (preSum[mid] < rand) {
	        			left = mid + 1;
	        		} else {
	        			right = mid;
	        		}
	        }
	        return rand <= preSum[left] ? left : right;
	    }
	}
}