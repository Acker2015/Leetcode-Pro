package lt_500_599;

/**
 * [521] Longest Uncommon Subsequence I
 */
public class LC_521 {
    /**
     * 这题很逗，只有相等的时候才会为-1
     * 仔细理解题意
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
