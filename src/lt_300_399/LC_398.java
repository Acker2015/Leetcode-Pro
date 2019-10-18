package lt_300_399;

import java.util.Random;
/**
 * 蓄水池抽样
 *
 * 算法首先创建一个长度为K的数组（蓄水池）用来存放结果，初始化为S的前k个元素，然后从k+1个元素开始迭代直到数组结束。
 * 算法生成一个随机数j∈[1, i]，如果 j ≤ k，那么蓄水池的第 j 个元素被替换为S的第i个元素。
 * @author Acker
 *
 */
public class LC_398 {
	int[] nums;
	public LC_398(int[] nums) {
        this.nums = nums;
    }
    
    public int pick(int target) {
        int count = 1;
        Random random = new Random();
        int aimI = -1;
        for (int i = 0; i < nums.length; ++i) {
        		if (nums[i] == target) {
        			if (random.nextInt(count++) == 0) {
        				aimI = i;
        			}
        		}
        }
        return aimI;
    }
	public static void main(String[] args) {
		int[] test = {1,2,3,4,5,3,3,3};
		LC_398 lc_398 = new LC_398(test);
		System.out.println(lc_398.pick(3));
	}
}
