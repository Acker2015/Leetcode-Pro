package other.O1Point3Acre.ms;


public class M_015 {
    static class Point {
        int x,y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Circle {
        Point center;
        int radius;
    }
    static class Line {
        Point p1, p2;
    }

    private double pointDistance(Point p, Point q) {
        int dx = p.x - q.x;
        int dy = p.y - q.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    private double pointProduct(Point p, Point q) {
        return p.x*q.x + p.y*q.y;
    }

    /**
     * 点到线段的最短距离
     * @param p
     * @param line
     * @return
     */
    private double disBetweenPointAndLine(Point p, Line line) {
        Point v = new Point(line.p1.x-line.p2.x, line.p1.y-line.p2.y);
        Point v1 = new Point(p.x-line.p1.x, p.y-line.p2.y);
        Point v2 = new Point(p.x-line.p2.x, p.y-line.p2.y);
        double product1 = pointProduct(v, v1);
        double product2 = pointProduct(v, v2);
        if (product1*product2 >= 0) {
            return Math.min(pointDistance(p, line.p1), pointDistance(p, line.p2));
        } else {
            // 最短距离为点到直线的最短距离
            double dis1 = pointDistance(p, line.p1);
            double dis2 = pointDistance(line.p1, line.p2);
            double angle = Math.acos(product1 / (dis1 * dis2));
            return dis1 * Math.sin(angle);
        }
    }

    /**
     * 圆与线段之间的位置关系
     * @param circle
     * @param line
     * @return
     */
    public String relationBetweenLineAndCircle(Circle circle, Line line) {
        double minDis = disBetweenPointAndLine(circle.center, line);
        // 求两端点到圆心的距离
        double dis1 = pointDistance(circle.center, line.p1);
        double dis2 = pointDistance(circle.center, line.p2);
        if (dis1 <= circle.radius && dis2 <= circle.radius) {
            return "线段在圆心内部";
        } else if (minDis <= circle.radius) {
            return "相交";
        } else {
            return "线段在圆的外部";
        }
    }

}
