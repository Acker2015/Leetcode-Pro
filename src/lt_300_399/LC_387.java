package lt_300_399;

/**
 * [387] First Unique Character in a String
 */
public class LC_387 {

    /**
     * time O(n)
     * space O(1)
     */
    public int firstUniqChar(String s) {
        int[] letterArr = new int[26];
        char[] chs = s.toCharArray();
        for (char c: chs) {
            letterArr[c-'a']++;
        }
        for (int i = 0; i < chs.length; ++i) {
            if (letterArr[chs[i]-'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String ...srgs) {
        LC_387 lc_387 = new LC_387();
        String s = "aadadaad";
        System.out.println(lc_387.firstUniqChar(s));
    }
}
