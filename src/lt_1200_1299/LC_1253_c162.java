package lt_1200_1299;

import java.util.ArrayList;
import java.util.List;

/**
 * Greedy
 * 1253. Reconstruct a 2-Row Binary Matrix
 *
 * 1. 先统计出colsum[i]=2的位置个数 - 这些位置都是必然设置为1的位置
 * 2. 叠放放置 - 当第0层未达到upper的时候，优先放置upper
 *      如果第一层已经满员，那么尝试放置第二层
 *
 * 非法情况
 * 上下元素和不等于upper和lower
 */
public class LC_1253_c162 {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> ret = new ArrayList<>(2);
        ret.add(0, new ArrayList<>());
        ret.add(1, new ArrayList<>());
        int ans1 = 0, ans2 = 0; // 分别记录第一行和第二行1的出现次数
        // 先将必须填充1的位置计数
        for (int cnt: colsum) {
            if (cnt == 2) {
                ans1++;
                ans2++;
            }
        }
        for (int i = 0; i < colsum.length; ++i) {
            int[] tmp = new int[2];
            if (colsum[i] == 2) {
                tmp[0]=tmp[1]=1;
            } else if (colsum[i] == 1) {
                if (ans1 < upper) {
                    tmp[0] = 1;
                    ans1++;
                } else {
                    tmp[1] = 1;
                    ans2++;
                }
            }
            // middle check
            if (ans1 > upper || ans2 > lower) {
                ret.clear();
                break;
            }
            ret.get(0).add(tmp[0]);
            ret.get(1).add(tmp[1]);
        }
        // ret check
        if (ans1 != upper || ans2 != lower) {
            ret.clear();
        }
        return ret;
    }
}
