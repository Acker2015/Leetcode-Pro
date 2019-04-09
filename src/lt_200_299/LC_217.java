package lt_200_299;

import java.util.HashMap;
import java.util.Map;

/**
 * [217] Contains Duplicate
 */
public class LC_217 {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num: nums) {
            if (map.containsKey(num)) return true;
            map.put(num, true);
        }
        return false;
    }
}

