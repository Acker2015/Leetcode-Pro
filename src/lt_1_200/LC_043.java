package lt_1_200;

public class LC_043 {
	/**
     * 竖式乘法
     * Remember how we do multiplication?
		Start from right to left, perform multiplication on every pair of digits, and add them together.
		Let's draw the process! From the following draft, we can immediately conclude:
		`num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]` 
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] ret = new int[m+n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                int sum = mul + ret[i+j+1];
                ret[i+j+1] = sum % 10;
                ret[i+j] += sum/10;
            }
        }
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < m+n; ++i) {
            if (sbd.length() == 0 && ret[i] == 0) continue;
            sbd.append(String.valueOf(ret[i]));
        }
        if (sbd.length() == 0) sbd.append('0');
        return sbd.toString();
    }
	public static void main(String[] args) {
		String num1="9133";
		String num2 = "0";
		LC_043 lc_043 = new LC_043();
		System.out.println(lc_043.multiply(num1, num2));

	}

}
