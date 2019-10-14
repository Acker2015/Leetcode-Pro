package lt_1200_1299;

/**
 * 1221. Split a String in Balanced Strings
 *
 * 简单遍历
 */
public class LC_1221_c158 {
    public int balancedStringSplit(String s) {
        int num = 0;
        int l = 0, r = 0;
        char[] chs = s.toCharArray();
        for (char c: chs) {
            if (c == 'L') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                num++;
                l = r = 0;
            }
        }
        return (l==0&&r==0) ? num : 0;
    }

    public int balancedStringSplit2(String s) {
        int num = 0;
        int ans = 0;
        char[] chs = s.toCharArray();
        for (char c: chs) {
            ans += (c == 'L') ? 1:-1;
            if (ans==0) num++;
        }
        return ans==0?num:0;
    }
}
