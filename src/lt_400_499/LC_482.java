package lt_400_499;

/**
 * [482] License Key Formatting
 * String
 */
public class LC_482 {
    private final static char DASH = '-';
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder builder = new StringBuilder();
        char[] chs = S.toCharArray();
        int cnt = 0, idx = chs.length-1;
        while (idx >= 0) {
            if (chs[idx] != DASH)  {
                if (cnt == K) {
                    builder.append(DASH);
                    cnt = 0;
                }
                if (Character.isDigit(chs[idx])) {
                    builder.append(chs[idx]);
                } else {
                    builder.append(Character.toUpperCase(chs[idx]));
                }
                cnt++;
            }
            idx--;
        }
        return builder.reverse().toString();
    }
}
