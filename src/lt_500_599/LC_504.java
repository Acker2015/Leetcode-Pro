package lt_500_599;

/**
 * [504] Base 7
 */
public class LC_504 {
    /**
     * base 7
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean symbol = num > 0;
        StringBuilder builder = new StringBuilder();
        num = Math.abs(num);
        while (num != 0) {
            builder.insert(0, num%7);
            num /= 7;
        }
        if (!symbol) {
            builder.insert(0, '-');
        }
        return builder.toString();
    }
}
