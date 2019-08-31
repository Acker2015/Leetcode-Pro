package lt_500_599;

/**
 * [541] Reverse String II
 *
 * String
 */
public class LC_541 {
    public String reverseStr(String s, int k) {
        boolean reverseSymbol = true;   // 翻转标志
        if (k <= 1) return s;
        if (k >= s.length()) return new StringBuilder().append(s).reverse().toString();
        StringBuilder builder = new StringBuilder();
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i += k) {
            int left = i;
            int right = Math.min(left + k, chs.length);
            if (reverseSymbol) {
                while (right > left) {
                    builder.append(chs[--right]);
                }
            } else {
                while(left < right) {
                    builder.append(chs[left++]);
                }
            }
            reverseSymbol = !reverseSymbol;
        }
        return builder.toString();
    }
}
