package lt_200_299;

/**
 * [273] Integer to English Words
 */
public class LC_273 {
    private String[] n2eArr1 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] n2eArr2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] flagArr = {"", "Thousand", "Million", "Billion"};
    /**
     * 对三位数进行输出 num / 1000
     * 123 - One Hundred Twenty Three
     * 123 - 000 -Thousand
     * 123 - 000000 - Million
     */
    private String helper(int num) {
        String ret;
        if (num == 0) {
            ret = "";
        } else if (num < 20) {
            ret = n2eArr1[num] + " ";
        } else if (num < 100) {
            ret = n2eArr2[num/10] + " " + helper(num%10);
        } else {
            ret = n2eArr1[num/100] + " Hundred " + helper(num%100);
        }
        return ret.trim();
    }

    public String numberToWords(int num) {
        if (num < 20) {
            return n2eArr1[num];
        }
        String word = "";
        int i = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                word = helper(num%1000) + " " + flagArr[i] + " " + word;
            }
            i++;
            num = num/1000;
        }
        return word.trim();
    }

    public static void main(String ...args) {
        LC_273 lc_273 = new LC_273();
        System.out.println(lc_273.numberToWords(20));
    }
}
