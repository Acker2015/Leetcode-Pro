package lt_1_200;

import java.util.ArrayList;
import java.util.List;

public class LC_046 {
	/**
     * solution1
     * backtracking to get all permutations
     */
    public void permute(List<List<Integer>> retList, List<Integer> helpList, int[] nums, boolean[] visited) {
        if (helpList.size() == nums.length) {
            retList.add(new ArrayList<>(helpList));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (visited[i]) continue;
            visited[i] = true;
            helpList.add(nums[i]);
            permute(retList, helpList, nums, visited);
            visited[i]=false;
            helpList.remove(helpList.size()-1);
        }
    }
    /**
     * solution2
     * for [1, 2, 3]
     * permute(nums, 2) -> (1, 2) (2, 1)
     * permute(nums, 3) will add 3 into every sub combination of permute(nums, 2)
     * 
     * (1, 2) -> add 3 -> (3, 1, 2), (1, 3, 2), (1, 2, 3)
     * (2, 1) -> add 3 -> (3, 2, 1), (2, 3, 1), (2, 1, 3)
     *                      
     */
    public List<List<Integer>> permute(int[] nums, int n) {
        List<List<Integer>> retList = new ArrayList<>();
        if (n < 0) {
            retList.add(new ArrayList<>());
            return retList;
        }
        List<List<Integer>> tmpList = permute(nums, n - 1);
        for (int i = 0; i < tmpList.size(); ++i) {
            for (int j = 0; j <= tmpList.get(i).size(); ++j) {
                List<Integer> tmp = new ArrayList<>(tmpList.get(i));
                tmp.add(j, nums[n]);
                retList.add(tmp);
            }
        }
        return retList;
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length <= 0) return new ArrayList<>();
        return permute(nums, nums.length - 1);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
