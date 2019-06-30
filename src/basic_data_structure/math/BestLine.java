package basic_data_structure.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在二维平面上，有一些点，请找出经过点数最多的那条线
 * 直接通过两两点确定的斜率和截距来通过斜率统计最多的直线即可
 * 1. slope斜率为浮点数，对于存储比较需要使用epsilon极小的差值来协助比较 (slope, slope+epsilon, slope-epsilon)
 * 2. 注意斜率不存在的情况
 *
 * 对于所给的点如果都是整数的情况，可以选择将slope从浮点数边为使用Slope对象定义(代码中有定义)
 */
public class BestLine {
    private class GraphPoint {
        int x, y;
        GraphPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 斜率表达，由于slope为浮点数的时候需要考虑差值的问题，这里直接记录用分数表示(分子分母互质)
    private class Slope {
        int upper, down;
        boolean Symbol;
    }
    static private class Line {
        public static double epsilon = 0.0001;
        public double slope, intercept;
        private boolean infinite_slope = false;

        public Line(GraphPoint p, GraphPoint q) {
            if (Math.abs(p.x - q.x) > epsilon) {
                slope = (p.y-q.y)/(p.x-q.x);
                // y = kx + b
                intercept = p.y - slope * p.x;
            } else {
                infinite_slope = true;
                intercept = p.x;    // x轴截距
            }
        }

        /**
         * 平滑d
         * @param d
         * @return
         */
        public static double floorToNearestEpsilon(double d) {
            int r = (int) (d/epsilon);
            return r * epsilon;
        }

        public boolean isEquivalent(double a, double b) {
            return Math.abs(a-b) < epsilon;
        }

        /**
         * 直线判等操作
         * @param o
         * @return
         */
        public boolean isEquivalent(Object o) {
            Line line = (Line) o;
            return isEquivalent(this.slope, line.slope)
                    && isEquivalent(this.intercept, line.intercept)
                    && this.infinite_slope == line.infinite_slope;
        }
    }

    public static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        return gcd(b, a%b);
    }


    /**
     * 方法入口
     * 找出经过点数最多的那条线
     * @param points
     * @return
     */
    public Line findBestLine(GraphPoint[] points) {
        if (points.length <= 0) return null;
        Line bestLine = null;
        int maxCnt = 0;
        Map<Double, List<Line>> lineBySlope = new HashMap<>();
        for (int i = 0; i < points.length; ++i) {
            for (int j = i+1; j < points.length; ++j) {
                Line line = new Line(points[i], points[j]);
                this.insertLine(lineBySlope, line);
                int count = countEquivLines(lineBySlope, line);
                if (count > maxCnt) {
                    maxCnt = count;
                    bestLine = line;
                }
            }
        }
        return bestLine;
    }

    /**
     * 对
     * @param lineBySlope
     * @param line
     * @return
     */
    private int countEquivLines(Map<Double, List<Line>> lineBySlope, Line line) {
        double key = Line.floorToNearestEpsilon(line.slope);
        double eps = Line.epsilon;
        int count = countEquivalentLines(lineBySlope.get(key), line);
        count += countEquivalentLines(lineBySlope.get(key+eps), line);
        count += countEquivalentLines(lineBySlope.get(key-eps), line);
        return count;
    }
    /**
     * 对于slope相同的line寻找相同的line的出现次数
     * @param lines
     * @param line
     * @return
     */
    private int countEquivalentLines(List<Line> lines, Line line) {
        if (lines == null || lines.size() <= 0) return 0;
        int count = 0;
        for (Line parallelLine: lines) {
            if (parallelLine.isEquivalent(line)) {
                count++;
            }
        }
        return count;
    }

    private void insertLine(Map<Double, List<Line>> lineBySlope, Line line) {
        double key = Line.floorToNearestEpsilon(line.slope);
        lineBySlope.computeIfAbsent(key, k-> new ArrayList<>()).add(line);
    }

    public static void main(String ...args) {
        System.out.println(gcd(6, 9));
    }
}
