package secondround.sr1_300;

import java.util.ArrayList;
import java.util.List;

public class sr_046 {
    static class Solution1 {
        private void backtracking(int[] nums, boolean[] vis, List<Integer> helpList, List<List<Integer>> retList) {
            if (helpList.size() == nums.length) {
                List<Integer> ans = new ArrayList<>(helpList);
                retList.add(ans);
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (vis[i]) continue;
                vis[i] = true;
                helpList.add(nums[i]);
                backtracking(nums, vis, helpList, retList);
                helpList.remove(helpList.size()-1);
                vis[i] = false;
            }
        }
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> retList = new ArrayList<>();
            backtracking(nums, new boolean[nums.length], new ArrayList<>(nums.length), retList);
            return retList;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> retList = new Solution1().permute(new int[]{1,2,3,4});
        retList.forEach(list -> {
            list.forEach(item -> System.out.print(item + " "));
            System.out.println();
        });
    }
}
