package lt_200_299;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LC_219 {
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) {
                if (i-map.get(nums[i]) > k) {
                    set.add(nums[i]);
                } else if (set.contains(nums[i])){
                    set.remove(nums[i]);
                }
            }
            map.put(nums[i], i);
        }
        return set.size() <= 0;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i]) && i-map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String ...args) {
        int[] nums = {1,2,3,1,2,3};
        LC_219 lc_219 = new LC_219();
        System.out.println(lc_219.containsNearbyDuplicate(nums, 2));
    }
}
