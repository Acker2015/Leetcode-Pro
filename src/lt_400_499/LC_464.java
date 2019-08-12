package lt_400_499;

import java.util.HashMap;
import java.util.Map;

/**
 * 464-Can I Win
 * 记忆化搜索 memorization DP (memorization search)
 *
 * there are O(2^n) subproblems, so the complexity is O(2^n) after memorization. (Without memo, time complexity should be like O(n!))
 * 记忆化搜索 time: O(2^n)
 * 不使用记忆化搜索 time: O(n!)
 */
public class LC_464 {
    private Map<Integer, Boolean> map;  // 由于maxChoosableInteger只有20位，使用二进制来帮助记住组合情况
    private boolean[] used;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = maxChoosableInteger * (1 + maxChoosableInteger) / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 1) return true;
        this.used = new boolean[maxChoosableInteger+1];
        map = new HashMap<>();
        return helper(maxChoosableInteger, desiredTotal, 0);
    }

    /**
     * 目的使得对方保持失败，尽可能赢
     * 子问题(the state of the problem)
     * 1. the unchosen numbers
     * 2. the remaining desiredTotal to reach
     * @return  if it can win based on the left unchosen numbers and the remaining desired total.
     */
    private boolean helper(int maxChoosableInteger, int desiredTotal, int key) {
        if (desiredTotal <= 0) return false;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        for (int i = 1; i <= maxChoosableInteger; ++i) {
            if (used[i]) continue;
            used[i] = true;
            // 如选择i能够使对方输掉，那么本轮获胜目标达成
            if (!helper(maxChoosableInteger, desiredTotal-i, key|(1<<i))) {
                map.put(key, true);
                used[i] = false;
                return true;
            }
            used[i] = false;
        }
        map.put(key, false);
        return false;
    }
}
