package lt_1_200;

/**
 * [8] String to Integer (atoi)
 */
public class LC_008 {
    /**
     * 删除前导空格
     * 返回非空格起始索引位置
     * @param chs
     * @return
     */
    private int deleteLeadSpace(char[] chs) {
        int i = 0;
        while (i < chs.length && chs[i] == ' ') {
            i++;
        }
        return i;
    }

    /**
     * 判断是否越界
     * @param symbol
     * @param cur
     * @return
     */
    private boolean isOutOfRange(boolean symbol, long cur) {
        long contrastVal = symbol ? Integer.MAX_VALUE : ((long)Integer.MAX_VALUE + 1);
        return cur > contrastVal;
    }

    /**
     * 1. 注意处理前导空格
     * 2. 处理前置的符号位 +，-
     * 3. 整数越界处理，使用long来帮助处理
     */
    public int myAtoi(String str) {
        if (str == null || str.length() <= 0) return 0;
        char[] chs = str.toCharArray();
        int i = deleteLeadSpace(chs);
        if (i >= chs.length) return 0;
        boolean symbol = true;  // default positive
        long val = 0;
        if (chs[i] == '+' || chs[i] == '-') {
            symbol = chs[i++]=='+';
        }
        while (i < chs.length && !isOutOfRange(symbol, val)) {
            if (chs[i] < '0' || chs[i] > '9') {
                break;
            }
            val = val*10 + (chs[i++]-'0');
        }
        if (isOutOfRange(symbol, val)) {
            return symbol ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        val = (symbol ? 1 : -1) * val;
        return (int)val;
    }

    public static void main(String ...args) {
        LC_008 solution = new LC_008();
        assert solution.myAtoi(" +42-")==42;
        System.out.println(solution.myAtoi(" +42-"));
        System.out.println(solution.myAtoi("    -42skdha"));
        System.out.println(solution.myAtoi("    4193 with words"));
        System.out.println(solution.myAtoi("  -91283472332"));
        System.out.println(solution.myAtoi(String.valueOf(Integer.MAX_VALUE)));
        System.out.println(solution.myAtoi(String.valueOf(Integer.MAX_VALUE)+"1"));
    }
}
