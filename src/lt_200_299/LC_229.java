package lt_200_299;


import java.util.ArrayList;
import java.util.List;

public class LC_229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> retList = new ArrayList<>();
        int val1 = 0, ans1 = 0, val2 = 1, ans2 = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (val1 == nums[i]) {
                ans1++;
            } else if (val2 == nums[i]) {
                ans2++;
            } else {
                if (ans1 > 0 && ans2 > 0) {
                    ans1--;ans2--;
                } else if (ans1==0){
                    val1 = nums[i];
                    ans1++;
                } else {
                    val2 = nums[i];
                    ans2++;
                }
            }
        }
        ans1 = ans2 = 0;
        for (int num: nums) {
            if (num == val1) ans1++;
            if (num == val2) ans2++;
        }
        if (ans1 > nums.length/3) {
            retList.add(val1);
        }
        if (ans2 > nums.length/3) {
            retList.add(val2);
        }
        return retList;
    }
}
