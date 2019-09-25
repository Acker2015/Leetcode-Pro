package lt_400_499;

import java.util.*;

/**
 * [491] Increasing Subsequences
 */
public class LC_491 {
    private void dfs(int[] nums, int idx, LinkedList<Integer> helpList, List<List<Integer>> retList) {
        if (helpList.size() > 1) {
            retList.add(new ArrayList<>(helpList));
        }
        if (idx >= nums.length) return;
        Set<Integer> set = new HashSet<>();
        for (int i = idx; i < nums.length; ++i) {
            if (helpList.size() > 0 && helpList.peekLast() > nums[i]) continue;
            // if nums[i] has appear in the set, then should select it firstly
            if (set.contains(nums[i])) continue;
            set.add(nums[i]);

            helpList.add(nums[i]);
            dfs(nums, i + 1, helpList, retList);
            helpList.removeLast();
        }
    }
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        if (nums.length <= 0) {
            return retList;
        }
        dfs(nums, 0, new LinkedList<>(), retList);
        return retList;
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        int[] nums2 = {1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
        LC_491 lc_491 = new LC_491();
        List<List<Integer>> retList = lc_491.findSubsequences(nums2);
        for (List<Integer> list : retList) {
            list.forEach(item -> System.out.print(item + " "));
            System.out.println();
        }
    }
}
