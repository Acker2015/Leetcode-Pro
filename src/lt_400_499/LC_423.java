package lt_400_499;

/**
 * [423] Reconstruct Original Digits from English
 */
public class LC_423 {
    /**
     * 先从能唯一确定数字的字符串开始
     * zero -> z
     * two -> w
     * four -> u
     * six -> x
     * eight -> g
     * one -> o (o在0和4中已经用完)
     * three -> r
     * five -> f
     * seven -> v
     * nine -> i
     */
    String[][] digits = {{"zero","0"}, {"two","2"}, {"four","4"}, {"six", "6"},
            {"eight", "8"}, {"one", "1"}, {"three", "3"}, {"five", "5"}, {"seven", "7"}, {"nine", "9"}};
    public String originalDigits(String s) {
        int[] mem = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            mem[s.charAt(i)-'a']++;
        }
        StringBuilder builder = new StringBuilder();
        int[] ret = new int[10];
        for (int i = 0; i < digits.length; ++i) {
            int number = Integer.parseInt(digits[i][1]);
            char[] digit = digits[i][0].toCharArray();
            int minNum = Integer.MAX_VALUE;
            for (char c: digit) {
                minNum = Math.min(minNum, mem[c-'a']);
            }
            if (minNum > 0) {
                for (char c : digit) {
                    mem[c-'a'] -= minNum;
                }
                ret[number] = minNum;
            }
        }
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < ret[i]; ++j) {
                builder.append(i);
            }
        }
        return builder.toString();
    }
}
