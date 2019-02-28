package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_040 {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> ret = new ArrayList<>();
		Arrays.sort(candidates);
		backtracking(target, 0, new ArrayList<>(), ret, candidates);
		return ret;
    }
	
	private void backtracking(int remain, int start, List<Integer> helperList, List<List<Integer>> ret, int[] candidates) {
		// firstly, verify the remain == 0
		if (remain == 0) {
			ret.add(new ArrayList<>(helperList));
			return;
		}
		// secondly, check exception situation
		if (remain < 0 || start >= candidates.length) {
			return;
		}
		// backtracking
		for (int i=start; i < candidates.length; ++i) {
			if (i>start && candidates[i]==candidates[i-1]) continue;
			helperList.add(candidates[i]);
			backtracking(remain-candidates[i], i+1, helperList, ret, candidates);
			helperList.remove(helperList.size()-1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
