package lt_400_499;

/**
 * [443] String Compression
 */
public class LC_443 {
    private int numOfBit(int num) {
        int len = 0;
        while (num > 0) {
            len++;
            num = num/10;
        }
        return len;
    }
    public int compress(char[] chars) {
        int i = 0, len = chars.length, idx = 0;
        while (i < len) {
            int j = i + 1;
            while (j < len && chars[j]==chars[j-1]) j++;
            int num = j-i;
            // 要注意更新这个位置的字符啊啊啊啊啊啊
            chars[idx++] = chars[i];
            if (j - i > 1) {
                idx = idx + numOfBit(num);
                int ans = idx-1;
                while (num > 0) {
                    chars[ans--] = (char)(num%10 + '0');
                    num /= 10;
                }
            }
            i = j;
        }
        return idx;
    }

    public static void main(String ...args) {
        LC_443 lc_443 = new LC_443();
        char[] chs1 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char[] chs2 = {'a'};
        char[] chs3 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(lc_443.compress(chs3));
    }
}
