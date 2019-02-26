package lt_1_100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import sun.launcher.resources.launcher;
import sun.tools.jar.resources.jar;
/**
 * next permutation
 * 有序数据通过dfs可以构造出全部的组合
 * 
 * @author Acker
 *
 */
public class LC_031 {
	private void swap(int[] nums, int i, int k) {
		int tmp = nums[i];
		nums[i]=nums[k];
		nums[k]=tmp;
	}
	public void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;
        int maxIndex = nums.length-1;
        while (maxIndex-1 >= 0 && nums[maxIndex-1] >= nums[maxIndex]) {
            maxIndex--;
        }
        if (maxIndex==0) {
            Arrays.sort(nums);
            return;
        }
        int preIndex = maxIndex - 1, lastIndex = maxIndex;
        while (lastIndex+1<nums.length && nums[lastIndex+1]>nums[preIndex]) {
            lastIndex++;
        }
        int tmp = nums[preIndex];
        nums[preIndex] = nums[lastIndex];
        nums[lastIndex] = tmp;
        Arrays.sort(nums, maxIndex, nums.length);
    }
	/**
	 * 后一个全排列
	 * 				i	 j		 k
	 * 					 *	    	
	 * 				   *   *		 
	 * 				  *      *
	 * 				 *         *
	 *              *            *
	 *                 j为最高点
	 *                
	 *                
	 * * sketch :
	 * current: 3   7  6  2  5  4  3  1  .
	 *                    |  |     |     |
	 *          find i----+  j     k     +----end
	 * swap i and k :
	 *          3   7  6  3  5  4  2  1  .
	 *                    |  |     |     |
	 *               i----+  j     k     +----end
	 * reverse j to end :
	 *          3   7  6  3  1  2  4  5  .
	 *                    |  |     |     |
	 *          find i----+  j     k     +----end
	 *          
	 * a) 从后向前查找第一个相邻元素对(i,j)，并且满足A[i] < A[j]。易知，此时从j到end必然是降序。可以用反证法证明，请自行证明。
	 * b) 在[j,end)中寻找一个最小的k使其满足A[i]<A[k]。由于[j,end)是降序的，所以必然存在一个k满足上面条件；并且可以从后向前查找第一个满足A[i]<A[k]关系的k，此时的k必是待找的k。
	 * c) 将i与k交换。此时，i处变成比i大的最小元素，因为下一个全排列必须是与当前排列按照升序排序相邻的排列，故选择最小的元素替代i。易知，交换后的[j,end)仍然满足降序排序。因为在(k,end)中必然小于i，在[j,k)中必然大于k，并且大于i。
	 * d) 逆置[j,end) 由于此时[j,end)是降序的，故将其逆置。最终获得下一全排序。
	 * 
	 * 注意：如果在步骤a)找不到符合的相邻元素对，即此时i=begin，则说明当前[begin,end)为一个降序顺序，即无下一个全排列，STL的方法是将其逆置成升序。
	 * @param nums
	 */
	public void nextPermutation2(int[] nums) {
		if (nums.length <= 1) return;
		int i,j,k;
		// a
		for (i=nums.length-1; i>0;) {
			j=i--;
			if (nums[i]>=nums[j]) continue;
			// b
			for (k=nums.length-1; nums[i]>=nums[k]; k--);
			// c
			swap(nums, i, k);
			// d
			Arrays.sort(nums, j, nums.length);
		}
		Arrays.sort(nums);
	}
	/**
	 * 前一个全排列
	 * 				i	 j		 k
	 * 				*		     *	
	 * 				 * 		   *
	 * 				  *      *
	 * 				   *   *
	 *                   *
	 *                j为最低点
	 *                
	 * a) 从后向前查找第一个相邻元素对(i,j)，并且满足A[i] > A[j]。易知，此时从j到end必然是升序。可以用反证法证明，请自行证明。
	 * b) 在[j,end)中寻找一个最大的k使其满足A[i]>A[k]。由于[j,end)是升序的，所以必然存在一个k满足上面条件；并且可以从后往前查找第一个满足A[i]>A[k]关系的k，此时的k必是待找的k。
	 * c) 将i与k交换。此时，i处变成比i小的最大元素，因为上一个全排列必须是与当前排列按照降序排序相邻的排列，故选择最大的元素替代i。
	 * d) 逆置[j,end) 由于此时[j,end)是降序的，故将其逆置。最终获得下一全排序。
	 * @param nums
	 */
	public void prev_permutation(int[] nums) {
		if (nums.length <= 1) return;
		int i,j,k;
		for (i=nums.length-1; i>0;) {
			j = i--;
			if (nums[i]<=nums[j]) continue;
			for (k=nums.length-1; nums[i]<=nums[k]; k--);
			swap(nums, i, k);
			Arrays.sort(nums, j, nums.length);
		}
		Arrays.sort(nums);
	}
	
	public List<List<Integer>> permute(int[] nums, int idx) {
		if (nums.length <= 0) return new LinkedList<>();
		List<List<Integer>> ret = new LinkedList<>();
		if (idx == nums.length-1) {
			ret.add(Arrays.asList(nums[idx]));
			return ret;
		}
		List<List<Integer>> help = permute(nums, idx + 1);
		for (int i = 0; i < help.size(); ++i) {
			for (int j = 0; j <= help.get(i).size(); ++j) {
				List<Integer> tmp = new LinkedList<>();
				tmp.addAll(help.get(i).subList(0, j));
				tmp.add(nums[idx]);
				tmp.addAll(help.get(i).subList(j, help.get(i).size()));
				ret.add(tmp);
			}
		}
		return ret;
	}
	
	
	public static void main(String[] args) {
		int[] nums = {1,2,3};
		LC_031 lc_031 = new LC_031();
		List<List<Integer>> ret = lc_031.permute(nums, 0);
		for (int i = 0; i < ret.size(); ++i) {
			for (int j = 0; j < ret.get(i).size(); ++j) {
				System.out.print(ret.get(i).get(j)+" ");
			}
			System.out.println("");
		}

	}

}
