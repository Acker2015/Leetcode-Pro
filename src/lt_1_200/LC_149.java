package lt_1_200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [149] Max Points on a Line
 *
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class LC_149 {
    private static int generateGCD(int a, int b) {
        if (b == 0) return a;
        return generateGCD(b, a%b);
    }


    public static class Solution2 {
        /*
     *  A line is determined by two factors,say y=ax+b
     *
     *  If two points(x1,y1) (x2,y2) are on the same line(Of course).

     *  Consider the gap between two points.

     *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant

     *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b

     *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  So we can use y0&x0 to track a line;
     */
        /*
        遍历每个点，看它和后面的每个点构成的直线上有多少个点
        对每个点建立map，斜率是key
        斜率要用分数的形式，不要用double的形式存
        计算分数时先求分子分母的最大公约数gcd，再都除以gcd
        重合的点特殊处理
        */
        public int maxPoints(int[][] points) {
            int len = points.length;
            if (len <= 2) return len;
            int maxP = 2;
            for (int i = 0; i < len; ++i) {
                Map<String, Integer> map = new HashMap<>();
                int overlap = 0;
                int ans = 0;
                for (int j = i + 1; j < len; ++j) {
                    int dx = points[i][0]-points[j][0];
                    int dy = points[i][1]-points[j][1];
                    if (dx == 0 && dy == 0) {
                        overlap++;
                        continue;
                    }
                    // gcd符号与dx相同
                    int gcd = generateGCD(dx, dy);
                    dx /= gcd;
                    dy /= gcd;
                    String key = dx+"_"+dy;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                    ans = Math.max(ans, map.get(key));
                }
                maxP = Math.max(maxP, ans+overlap+1);
            }
            return maxP;
        }
    }


    public static void main(String[] args) {
        int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.maxPoints(points));

        System.out.println(generateGCD(-3, 6));
    }
}
