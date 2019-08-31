package lt_1_200;

/**
 * [65] Valid Number
 *
 * 首先根据用例确定规则 - 如果题目没有给出用例，自己能否设计出合理的用例？？
 * 0. 最多只能出现一个e，可以把字符串分成左右两个部分left, right
 * 1. left可以是整数或者小数，但是right只能是整数
 * 2. e的两边都至多出现一个符号位，并且都在符号位在left和right的起始位置
 * 3. left中只能包含起始符号位、小数点以及数字
 * 4. right中只能包括起始符号位、数字
 * 5. 需要去掉字符串前后space
 *
 * Numbers 0-9
 * Exponent - "e"
 * Positive/negative sign - "+"/"-"
 * Decimal point - "."
 *
 * "00.", ".0", ".5"都是符号规范的
 * " +0.5 " true
 * " -0.5e-20 " true
 * "20eab" false
 * "2-e10" false
 * "-000.3e-.30" false
 * "--6.9" false
 * "++372.3" false
 */
public class LC_065 {
    private boolean isSymbol(char c) {
        return c == '+' || c == '-';
    }

    /**
     * 1. 保证只有一个'.',并且'.'的位置出现在e的前边
     * 2. 保证只有一个e
     * 3. 不会出现除了'.','e',数字, 符号之外其他的字符
     * 4. 正负号最多出现两个
     * @param s
     * @return
     */
    private boolean preCheck(String s) {
        int eIdx = -1;
        int potIdx = -1;
        int symbolCnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '.') {
                // '.'只能出现一次，并且是在e的前边
                if (potIdx >= 0 || (eIdx >= 0)) return false;
                potIdx = i;
            } else if (c == 'e') {
                if (eIdx >= 0) return false;
                eIdx = i;
            } else if (c == '+' || c == '-') {
                symbolCnt++;
                if (symbolCnt > 2) return false;
                if (symbolCnt == 2 && eIdx < 0) return false;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    private boolean judgeFirstPart(String s, int end) {
        int start = isSymbol(s.charAt(0)) ? 1 : 0;
        for (int i = start; i < end; ++i) {
            if (isSymbol(s.charAt(i))) {
                return false;
            }
        }
        return (end-start > 1) || (end-start==1 && Character.isDigit(s.charAt(start)));
    }
    private boolean judgeLastPart(String s, int start) {
        if (start >= s.length()) return false;
        if (isSymbol(s.charAt(start))) {
            start++;
        }
        for (int i = start; i < s.length(); ++i) {
            if (isSymbol(s.charAt(i))) {
                return false;
            }
        }
        return start < s.length();
    }
    public boolean isNumber(String s) {
        if (s == null) return false;
        s = s.trim();
        if (s.length() <= 0) return false;
        if (!preCheck(s)) {
            return false;
        }
        int idx = s.indexOf("e");
        if (idx < 0) {
            return judgeFirstPart(s, s.length());
        } else {
            return judgeFirstPart(s, idx) && judgeLastPart(s, idx+1);
        }
    }

    /**
     "0" => true
     " 0.1 " => true
     "abc" => false
     "1 a" => false
     "2e10" => true
     " -90e3   " => true
     " 1e" => false
     "e3" => false
     " 6e-1" => true
     " 99e2.5 " => false
     "53.5e93" => true
     " --6 " => false
     "-+3" => false
     "95a54e53" => false
     * @param args
     */
    public static void main(String ...args) {
        LC_065 lc_065 = new LC_065();
        System.out.println(lc_065.isNumber("0"));
        System.out.println(lc_065.isNumber(" 0.1  "));
        System.out.println(lc_065.isNumber("abc"));
        System.out.println(lc_065.isNumber("1 a"));
        System.out.println(lc_065.isNumber("2e10"));
        System.out.println(lc_065.isNumber(" -90e3"));
        System.out.println(lc_065.isNumber(" 1e"));
        System.out.println(lc_065.isNumber(" e3"));
        System.out.println(lc_065.isNumber(" 6e-1"));
        System.out.println(lc_065.isNumber("99e2.5"));
        System.out.println(lc_065.isNumber("53.5e93"));
        System.out.println(lc_065.isNumber(" --6 "));
        System.out.println(lc_065.isNumber(" -+3"));
        System.out.println(lc_065.isNumber("95a54e53"));

    }
}
