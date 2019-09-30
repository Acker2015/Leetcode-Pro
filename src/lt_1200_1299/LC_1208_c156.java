package lt_1200_1299;

/**
 * 1208. Get Equal Substrings Within Budget
 * Contest 156
 * Q2
 *
 * Sliding window
 *
 */
public class LC_1208_c156 {
    // sliding window
    public int equalSubstring(String s, String t, int maxCost) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int start = 0, end = 0, maxLength = 0, cost = 0;
        while (end < sArr.length) {
            cost += Math.abs(sArr[end] - tArr[end]);
            end++;
            // move the window to satisfy the maxCost
            while (start < end && cost > maxCost) {
                cost -= Math.abs(sArr[start] - tArr[start]);
                start++;
            }
            // compare and set the maxLength.
            maxLength = Math.max(end - start, maxLength);
        }
        return maxLength;
    }
}
