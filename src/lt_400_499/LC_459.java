package lt_400_499;

/**
 * [459] Repeated Substring Pattern
 */
public class LC_459 {
    /**
     * 找到每个有可能的repeat substring, 判断是否合法即可
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() <= 1) return false;
        char[] chs = s.toCharArray();
        int len = chs.length;
        for (int i = 1; 2*i <= len; ++i) {
            // 剪枝
            if (chs[i] != chs[0] || len % i != 0) continue;
            String temp = s.substring(0, i);
            int j = i;
            while (j < len) {
                if (!temp.equals(s.substring(j, j+i))) {
                    break;
                }
                j += i; // i is the length of repeat substring.
            }
            if (j == len) return true;
        }
        return false;
    }
}
