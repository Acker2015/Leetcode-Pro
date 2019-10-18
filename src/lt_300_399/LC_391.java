package lt_300_399;

import java.util.HashSet;
import java.util.Set;

/**
 * [391] Perfect Rectangle
 * graph
 * 满足两个条件即可
 */
public class LC_391 {
    /**
     * The right answer must satisfy two conditions:
     * 1. the large rectangle area should be equal to the sum of small rectangles
     *    (最后大矩形的面积等于各个矩形区域的面积之和)
     * 2. count of all the points should be even, and that of all the four corner points should be one
     *    (除了最后大矩形的四个端点坐标会出现一次，其他的矩形顶点坐标都会出现偶数次-因为内部矩形顶点需要重合消除-对对消除)
     */
    public boolean isRectangleCover(int[][] rectangles) {
        Set<String> set = new HashSet<>();
        int sum = 0;
        // 记录大矩形的左下坐标和右上坐标
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
        for (int[] rect: rectangles) {
            sum += (rect[3]-rect[1])*(rect[2]-rect[0]);
            x1 = Math.min(x1, rect[0]);
            y1 = Math.min(y1, rect[1]);
            x2 = Math.max(x2, rect[2]);
            y2 = Math.max(y2, rect[3]);
            String p1 = rect[0]+" "+rect[1];
            String p2 = rect[0]+" "+rect[3];
            String p3 = rect[2]+" "+rect[1];
            String p4 = rect[2]+" "+rect[3];
            if (!set.add(p1)) set.remove(p1);
            if (!set.add(p2)) set.remove(p2);
            if (!set.add(p3)) set.remove(p3);
            if (!set.add(p4)) set.remove(p4);
        }
        if (set.size() != 4
                || !set.contains(x1+" "+y1)
                || !set.contains(x2+" "+y2)
                || !set.contains(x1+" "+y2)
                || !set.contains(x2+" "+y1)) return false;
        return (x2-x1)*(y2-y1)==sum;
    }
}
