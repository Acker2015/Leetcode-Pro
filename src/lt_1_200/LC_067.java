package lt_1_200;

public class LC_067 {
    /**
     * Input: a = "1010", b = "1011"
     * Output: "10101"
     * 直接从尾部遍历相加即可
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if (a.length() <= 0) return b;
        if (b.length() <= 0) return a;
        StringBuilder sbd = new StringBuilder();
        int i = a.length()-1, j =b.length()-1;
        int sum = 0;
        while(i>=0 || j>=0) {
            if (i >= 0) {
                sum += (a.charAt(i--)-'0');
            }
            if (j >= 0) {
                sum += (b.charAt(j--)-'0');
            }
            sbd.append(String.valueOf(sum%2));
            sum = sum/2;
        }
        if (sum > 0) sbd.append(String.valueOf(sum));
        return sbd.reverse().toString();
    }
    public static void main(String ...args) {
        LC_067 lc_067 = new LC_067();
        System.out.println(lc_067.addBinary("1010", "1011"));
    }
}
