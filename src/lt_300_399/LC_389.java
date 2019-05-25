package lt_300_399;

// [389] Find the Difference
public class LC_389 {
    /**
     * bit-manipulation
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference1(String s, String t) {
        int num = 0;
        char[] sArr = s.toCharArray(), tArr = t.toCharArray();
        for (char c: sArr) {
            num ^= 1 << (c-'a');
        }
        for (char c: tArr) {
            num ^= 1 << (c-'a');
        }
        //System.out.println(num);
        for (int i = 0; i < 26; ++ i) {
            if ((num&1) > 0) {
                return  (char)('a' + i);
            }
            num>>=1;
        }
        return '0';
    }

    /**
     * hashmap
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int[] mem = new int[128];
        char[] sArr = s.toCharArray(), tArr = t.toCharArray();
        for (char c: sArr) {
            mem[c]++;
        }
        for (char c: tArr) {
            if (--mem[c] < 0) {
                return c;
            }
        }
        return '0';
    }
}
