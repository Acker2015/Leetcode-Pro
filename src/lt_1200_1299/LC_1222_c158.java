package lt_1200_1299;

import java.util.ArrayList;
import java.util.List;

/**
 * 1222. Queens That Can Attack the King
 *
 * 8个方向查找即可
 */
public class LC_1222_c158 {
    private int m, n;
    private List<Integer> search(boolean[] queen, int[] king, int dx, int dy) {
        int x = king[0]+dx;
        int y = king[1]+dy;
        while (x>=0 && y>=0 && x<m && y<n) {
            if (queen[x*n+y]) {
                List<Integer> ret = new ArrayList<>();
                ret.add(x);
                ret.add(y);
                return ret;
            }
            x += dx;
            y += dy;
        }
        return null;
    }
    private void add(List<List<Integer>> retList, List<Integer> sub) {
        if (sub != null) {
            retList.add(sub);
        }
    }
    // 八个方向找即可
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        m = n = 8;
        List<List<Integer>> retList = new ArrayList<>();
        boolean[] mem = new boolean[m*n];
        for (int[] queen: queens) {
            mem[queen[0]*n+queen[1]] = true;
        }
        add(retList, search(mem, king, -1, 0));
        add(retList, search(mem, king, 1, 0));
        add(retList, search(mem, king, 0, 1));
        add(retList, search(mem, king, 0, -1));
        add(retList, search(mem, king, -1, -1));
        add(retList, search(mem, king, 1, 1));
        add(retList, search(mem, king, -1, 1));
        add(retList, search(mem, king, 1, -1));
        return retList;
    }
}
