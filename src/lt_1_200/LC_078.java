package lt_1_200;

import java.util.ArrayList;
import java.util.List;

public class LC_078 {
	/**
     * 回溯
     */
    public void backtracking(int index, int[] nums, List<Integer> subList, List<List<Integer>> retList) {
        retList.add(new ArrayList<>(subList));
        if (index == nums.length) return;
        // 从index处开始，之前没有选择的节点不再重新绕回去选择
        for (int i = index; i < nums.length; ++i) {
            subList.add(nums[i]);
            backtracking(i+1, nums, subList, retList);
            subList.remove(subList.size()-1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        backtracking(0, nums, new ArrayList<>(), retList);
        return retList;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
