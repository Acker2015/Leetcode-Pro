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

    /**
     * time O(n^2)
     * space constant space
     * 冒泡排序
     * @param s
     * @return
     */
    public static String moveLetters2(String s) {
        if (s.length() <= 1) return s;
        char[] chs = s.toCharArray();
        int idx = 0;
        for (int i = 0; i < chs.length; i++) {
            if (Character.isLowerCase(chs[i])) {
                char tmp = chs[i];
                for (int j = i-1; j >= idx; --j) {
                    chs[j+1] = chs[j];
                }
                chs[idx++] = tmp;
            }
        }
        return String.valueOf(chs);
    }


    public static String moveLetters3(String s) {
        if (s.length() <= 1) return s;
        char[] chs = s.toCharArray();
        int i = -1, j = 0;
        while (j < s.length()) {
            if (chs[j] >= 'A' && chs[j] <= 'Z') {
                j++;
            } else {
                int k = j;
                char ans = chs[j];
                while (k-1 > i) {
                    chs[k] = chs[k-1];
                    k--;
                }
                chs[k] = ans;
                i++;
                j++;
            }
        }
        return String.valueOf(chs);

    }

    public static void main(String... args) {
        String s = "AbCDEFghIJkopqXYZ";
        System.out.println(moveLetters1(s));
        System.out.println(moveLetters2(s));
        System.out.println(moveLetters3(s));
    }

}
