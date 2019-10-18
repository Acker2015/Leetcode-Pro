package lt_300_399;

import java.util.HashMap;
import java.util.Map;

/**
 * O(n)算法
 * 记住最近的一次上层的路径长度即可
 *
 * hash-map
 *
 * [388] Longest Absolute File Path
 */
public class LC_388 {
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        Map<Integer, Integer> levelMap = new HashMap<>();
        char[] chs = input.toCharArray();
        int i = 0;
        while (i < chs.length) {
            int level = 0;
            int j = i;
            while (j < chs.length && chs[j] == '\t') {
                j++;
            }
            level = j-i;
            boolean isFile = false;
            while (j < chs.length && chs[j] != '\n') {
                if (chs[j] == '.') {
                    isFile = true;
                }
                j++;
            }
            int curLength = j - i - level;
            int lastLevelLength = levelMap.getOrDefault(level-1, 0);
            if (isFile) {
                maxLen = Math.max(maxLen, curLength+lastLevelLength+level); // level means num of the character '/'
            } else {
                levelMap.put(level, lastLevelLength+curLength);
            }
            i = j+1;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        System.out.println(new LC_388().lengthLongestPath(s));
    }
}
