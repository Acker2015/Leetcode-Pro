package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_090 {
	private void backtracking(int index, int[] nums, boolean[] visited, List<Integer> helper, List<List<Integer>> retList) {
        if (index == nums.length) {
            retList.add(new ArrayList<>(helper));
            return;
        }
        backtracking(index+1, nums, visited, helper, retList);
        // 如果前一个重复的数没有被选择
        if (index > 0 && nums[index-1]==nums[index] && !visited[index-1]){
            return;
        }
        helper.add(nums[index]);
        visited[index] = true;
        backtracking(index+1, nums, visited, helper, retList);
        visited[index]=false;
        helper.remove(helper.size()-1);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    		Arrays.sort(nums);
        List<List<Integer>> retList = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtracking(0, nums, visited, new ArrayList<>(), retList);
        return retList;
    }
	public static void main(String[] args) {
		int[] nums = {4,1,4,4,4};
		LC_090 lc_090 = new LC_090();
		List<List<Integer>> ret = lc_090.subsetsWithDup(nums);
		for (int i = 0; i < ret.size(); ++i) {
			System.out.println(ret.get(i));
		}

	}

}
