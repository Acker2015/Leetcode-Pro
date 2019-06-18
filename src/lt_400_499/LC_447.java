package lt_400_499;

import java.util.HashMap;
import java.util.Map;

/**
 * [447] Number of Boomerangs
 * O(n^2) time
 * O(n) space
 */
public class LC_447 {
    private int getDis(int[]a, int[] b) {
        int dx = a[0]-b[0];
        int dy = a[1]-b[1];
        return dx*dx+dy*dy;
    }
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            map.clear();
            // 寻找到points[i]距离相同的点，可以全排列得到解
            for (int j = 0; j < points.length; ++j) {
                //if (i==j) continue;
                int dis = getDis(points[i], points[j]);
                map.put(dis, map.getOrDefault(dis, 0)+1);
            }
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                // 全排列，组合个数为x*(x-1)
                ret += entry.getValue() * (entry.getValue()-1);
            }
        }
        return ret;
    }
}
