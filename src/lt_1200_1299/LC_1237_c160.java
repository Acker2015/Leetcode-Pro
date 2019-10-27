package lt_1200_1299;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1237. Find Positive Integer Solution for a Given Equation
 */
public class LC_1237_c160 {
    public interface CustomFunction {
        int f(int x, int y);
    }

    /**
     * O(N)
     */
    static class Solution1 {
        // O(n) 类似矩阵问题
        //假设x=0的时候满足条件的y=y0
        //那么x=1的时候满足条件的y必然小于y0, 因为根据题目条件f(0, y0) < f(1, y0)
        public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
            List<List<Integer>> ret = new ArrayList<>();
            int y = 1000;
            for (int x = 1; x <= 1000 && y > 0; ++x) {
                while (y > 0 && customfunction.f(x, y) > z) {
                    y--;
                }
                if (y > 0 && customfunction.f(x, y) == z) {
                    ret.add(Arrays.asList(x, y));
                }
            }
            return ret;
        }
    }

    /**
     * 直白解法
     * O(n^2)
     */
    static class Solution2 {
        public List<List<Integer>> findSolution1(CustomFunction customfunction, int z) {
            List<List<Integer>> retList = new ArrayList<>();
            int max = 1000;
            for (int i = 1; i <= max; ++i) {
                for (int j = 1; j <= max; ++j) {
                    if (customfunction.f(i, j) == z) {
                        retList.add(Arrays.asList(i, j));
                        break;
                    }
                }
            }
            return retList;
        }
    }

    /**
     * avg time < O(N)
     * 二分参与
     */
    static class Solution3 {
        // 二分
        public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
            List<List<Integer>> ret = new ArrayList<>();
            int y = 1001;
            for (int x = 1; x <= 1000 && y > 0; ++x) {
                // 二分不断压缩右边界
                int l = 1, r = y, m;
                while (l < r) {
                    m = l + (r - l) / 2;
                    if (customfunction.f(x, m) < z) {
                        l = m+1;
                    } else {
                        r = m;
                    }
                }
                if (customfunction.f(x, l) == z) {
                    ret.add(Arrays.asList(x, l));
                    y = l-1;
                } else {
                    y = l;
                }
            }
            return ret;
        }
    }



}
