package lt_1200_1299;

import java.util.ArrayList;
import java.util.List;

/**
 * 1238. Circular Permutation in Binary Representation
 *
 * 由于2^n个数排列，相邻数字只有一个bit位置不同
 * 那么自然想到这是一个格雷码的问题(gray code)
 *
 * 这里只需要在gray code的基础上，将起始数字排成start开始即可
 *
 * link to leetcode-89[gray code]
 */
public class LC_1238_c160 {
    public List<Integer> circularPermutation(int n, int start) {
        int num = (int) Math.pow(2, n);
        List<Integer> preList = new ArrayList<>();
        List<Integer> curList = new ArrayList<>(num);
        boolean startVis = false;
        for (int i = 0; i < num; ++i) {
            int ans = i^(i>>1);
            if (ans == start) {
                startVis = true;
            }
            if (startVis) {
                curList.add(ans);
            } else {
                preList.add(ans);
            }
        }
        curList.addAll(preList);
        return curList;
    }
}
