package lt_1200_1299;

import java.util.ArrayList;
import java.util.List;

/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 *
 * DFS, Backtracking
 *
 * 将word映射到hash值来帮助判断unique
 */
public class LC_1239_c160 {
    private int maxLen;
    // 计算hash
    private int hash(String word) {
        char[] chs = word.toCharArray();
        int ans = 0;
        for (char c: chs) {
            int mask = 1<<(c-'a');
            if ((ans&mask) > 0) return -1;
            ans |= mask;
        }
        return ans;
    }
    // 计算结果有多少个字母
    private int calLength(int ans) {
        int len = 0;
        while (ans != 0) {
            len++;
            ans = ans&(ans-1);
        }
        return len;
    }
    private void backtracking(List<Integer> keys, int idx, int ans) {
        if (idx >= keys.size()) {
            maxLen = Math.max(maxLen, calLength(ans));
            return;
        }
        if ((ans&keys.get(idx)) == 0) {
            backtracking(keys, idx+1, ans|keys.get(idx));
        }
        backtracking(keys, idx+1, ans);
    }


    public int maxLength(List<String> arr) {
        int len = arr.size();
        if (len <= 0) return 0;
        List<Integer> keys = new ArrayList<>(len);
        // 转换成hash值的列表 - 本身带有重复字母的忽略
        for (String word: arr) {
            int code = hash(word);
            if (code > 0) {
                keys.add(code);
            }
        }
        maxLen = 0;
        backtracking(keys, 0, 0);
        return maxLen;
    }

}
