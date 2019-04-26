package other.O1Point3Acre.ms;

/**
 * 给一个字符串，有大写和小写字母，要把所有大写字母移动到小写字母后面并保持顺序不变。
 *
 */
public class M_002_MoveLetters {
    /**
     * time O(n)
     * space O(n)
     * @param s
     * @return
     */
    public static String moveLetters1(String s) {
        StringBuilder sbd1 = new StringBuilder();
        StringBuilder sbd2 = new StringBuilder();
        char[] chs = s.toCharArray();
        for (char c: chs) {
            if (c >='a' && c <='z') {
                sbd1.append(c);
            }
            if (c >= 'A' && c <= 'Z') {
                sbd2.append(c);
            }
        }
        return sbd1.toString()+sbd2.toString();
    }

    private static int findNextLowCase(char[] chs, int idx) {
        for (int i = idx + 1; i < chs.length; ++i) {
            if (chs[i] >= 'a' && chs[i] <= 'z') {
                return i;
            }
        }
        return -1;
    }

    /**
     * time O(n^2)
     * space constant space
     * @param s
     * @return
     */
    public static String moveLetters2(String s) {
        if (s.length() <= 1) return s;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] >= 'A' && chs[i] <= 'Z') {
                // 如果是大写，找下一个小写的位置
                int idx = findNextLowCase(chs, i);
                if (idx < 0) {
                    break;
                }
                char ans = chs[idx];
                // 将[i, idx)之间的大写字母顺序平移
                while (idx > i) {
                    chs[idx] = chs[idx-1];
                    idx--;
                }
                // i处的字符置为找到的上述小写字母
                chs[i] = ans;
            }
        }
        return String.valueOf(chs);
    }

    public static void main(String... args) {
        String s = "AbCDEFghIJkopqXYZ";
        System.out.println(moveLetters1(s));
        System.out.println(moveLetters2(s));
    }

}
