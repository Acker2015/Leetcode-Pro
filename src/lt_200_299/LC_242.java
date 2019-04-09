package lt_200_299;

public class LC_242 {
    /**
     *
     * [242] Valid Anagram
     * 开一个256的数组对每个ASCII码进行计数
     * s中的字符对应位+1
     * t中的字符对应位-1
     * 最后数组中每一个位置都为0就表示s和t为同构字符串
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] arr = new int[256];
        int len = s.length();
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        for (int i = 0; i < len; ++i) {
            arr[sArr[i]]++;
            arr[tArr[i]]--;
        }
        for (int i = 0; i < 256; ++i) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
