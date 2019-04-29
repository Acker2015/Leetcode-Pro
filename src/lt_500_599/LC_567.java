package lt_500_599;


/**
 * [567] Permutation in String
 * 滑动窗口
 */
public class LC_567 {
    private boolean isAllZero(int[] temp) {
        for (int i = 0; i < 26; ++i) {
            if (temp[i] != 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * sliding window
     * 保持len1长度的窗口，如果窗口内的各种字符个数相等，那么就满足题目条件
     *
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] temp = new int[26];
        for (int i = 0; i < len1; ++i) {
            temp[s1.charAt(i)-'a']++;
            temp[s2.charAt(i)-'a']--;
        }
        if (isAllZero(temp)) {
            return true;
        }
        for (int i = len1; i < len2; ++i) {
            temp[s2.charAt(i)-'a']--; // s2新位置占用s1字符个数
            temp[s2.charAt(i-len1)-'a']++;  // 窗口外的字符恢复s1在对应位置的字符
            if (isAllZero(temp)) {
                return true;
            }
        }
        return false;
    }

    public static void main (String ...args) {
        String s1 = "ab";
        String s2 = "eidboaoo";
        System.out.println(new LC_567().checkInclusion(s1, s2));
    }
}
