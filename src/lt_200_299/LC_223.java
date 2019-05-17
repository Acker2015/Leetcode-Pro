package lt_200_299;

/**
 * [223] Rectangle Area
 * Math
 */
public class LC_223 {
    /**
     * 如果求得重合部分的面积？
     * 宽=右边的最小值-左边的最大值 （宽如果为负数，表示不重合）
     * 长=上边的最小值-下边的最大值 （长如果为负数，表示不重合）
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = Math.abs(A-C)*Math.abs(B-D);
        int area2 = Math.abs(E-G)*Math.abs(F-H);
        // 求重合部分的面积
        // 宽=右边的最小值-左边的最大值
        // 长=上边的最小值-下边的最大值
        int maxLeft = Math.max(A, E);
        int minRight = Math.min(C, G);
        int maxBottom = Math.max(B, F);
        int minTop = Math.min(D, H);
        if (maxLeft >= minRight || maxBottom >= minTop) {
            return area1+area2;
        } else {
            return area1+area2-(minRight-maxLeft)*(minTop-maxBottom);
        }
    }
}
