package lt_300_399;

/**
 * [306-Additive Number](https://leetcode.com/problems/additive-number/description/)
 * string
 */
public class LC_306 {
    // 加法运算
    private String plus(String s1, String s2) {
        StringBuilder builder = new StringBuilder();
        int i = s1.length()-1, j = s2.length()-1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += s1.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += s2.charAt(j--) - '0';
            }
            builder.insert(0, sum%10);
            carry = sum / 10;
        }
        if (carry > 0) {
            builder.insert(0, carry);
        }
        return builder.toString();
    }
    // 递归判断
    public boolean judge(String num, String a, String b, int j) {
        if (j == num.length()) {
            return true;
        }
        // 剪枝
        if (j + Math.max(a.length(), b.length()) > num.length()) {
            return false;
        }
        String ans = plus(a, b);
        int ansLen = ans.length();
        if (j + ansLen > num.length() || !num.substring(j, j+ansLen).equals(ans)) {
            return false;
        }
        return judge(num, b, ans, j + ansLen);
    }
    /**
     * 挑选前两个数
     * 那么后续直接可以按照特性来判断
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        char[] numArr = num.toCharArray();
        // a -> [0, i), b -> [i, j)
        for (int i = 1; i < len; ++i) {
            // 长度大于1，不能有前导0
            if (i > 1 && numArr[0] == '0') break;
            for (int j = i + 1; j < len; ++j) {
                // 长度大于1，不能有前导0
                if (j > i + 1 && numArr[i] == '0') break;
                if (judge(num, num.substring(0, i), num.substring(i, j), j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String ...args) {
        String a = "4567", b = "123";
        LC_306 lc_306 = new LC_306();
        System.out.println(lc_306.isAdditiveNumber("000"));
    }
}
