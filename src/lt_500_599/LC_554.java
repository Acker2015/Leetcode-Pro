package lt_500_599;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [554] Brick Wall
 */
public class LC_554 {
    /**
     * 直接hashmap记录除了墙两边之后的每个砖的边缘的位置个数
     */
    public int leastBricks(List<List<Integer>> wall) {
        if (wall.size() <= 0) return 0;
        int maxRepeatCorn = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int pos = 0;
            // 墙的左边和右边都不要考虑，因为墙的两边是自然对其的
            for (int i = 0; i < row.size() - 1; ++i) {
                pos += row.get(i);
                int brickEdgeNum = map.getOrDefault(pos, 0) + 1;
                map.put(pos, brickEdgeNum);
                maxRepeatCorn = Math.max(maxRepeatCorn, brickEdgeNum);
            }
        }
        return wall.size() - maxRepeatCorn;
    }
}
