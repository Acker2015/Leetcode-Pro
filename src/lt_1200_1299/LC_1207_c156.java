package lt_1200_1299;

import java.util.HashMap;
import java.util.Map;

/**
 * 1207. Unique Number of Occurrences
 * Contest 156
 * Q1
 *
 * HashMap
 */
public class LC_1207_c156 {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = arr.length;
        if (len <= 1) return true;
        boolean[] mem = new boolean[len+1];
        // statistic numbers for each item in arr
        for (int num: arr) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        // find the repeated num based on the appear numbers.
        for (Integer key: map.keySet()) {
            int cnt = map.get(key);
            if (mem[cnt]) return false;
            mem[cnt] = true;
        }
        return true;
    }
}
