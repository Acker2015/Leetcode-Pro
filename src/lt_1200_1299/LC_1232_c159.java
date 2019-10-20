package lt_1200_1299;


/**
 * 利用斜率公式slope
 * 对于两个点(x1, y1), (x2, y2). 这条线的斜率为(y2-y1)/(x2-x1)
 * 那么对于第三个点(x3, y3)
 *
 * (y3-y1)/(x3-x1) = slope = (y2-y1)/(x2-x1)
 * 防止分母为0的存在，这里使用乘法替换
 *
 * (y3-y1)*(x2-x1) = (y2-y1)*(x3-x1)
 *
 * O(N)
 */
public class LC_1232_c159 {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length <= 2) return true;
        int dx = coordinates[1][0]-coordinates[0][0];
        int dy = coordinates[1][1]-coordinates[0][1];
        for (int i = 2; i < coordinates.length; ++i) {
            int ndx = coordinates[i][0] - coordinates[0][0];
            int ndy = coordinates[i][1] - coordinates[0][1];
            // dy/dx = ndy/ndx -> dy*ndx=dx*ndy
            if (dy*ndx!=dx*ndy) {
                return false;
            }
        }
        return true;
    }
}
