package lt_200_299;

import java.util.ArrayList;
import java.util.List;

/**
 * [216] Combination Sum III
 *
 * 回溯法-backtracking
 */
public class LC_216 {
    private void combine(List<List<Integer>> retList, List<Integer> midList, int k, int left, int cur) {
        if (cur > 9) return;
        if (left < cur || midList.size() >= k) return;
        if (left == cur) {
            if (midList.size() != k-1) return;
            List<Integer> ans = new ArrayList<>(midList);
            ans.add(cur);
            retList.add(ans);
            return;
        }
        midList.add(cur);
        combine(retList, midList, k, left-cur, cur+1);
        midList.remove(midList.size()-1);
        combine(retList, midList, k, left, cur+1);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> retList = new ArrayList<>();
        combine(retList, new ArrayList<>(), k, n, 1);
        return retList;
    }
}
