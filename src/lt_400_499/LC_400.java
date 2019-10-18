package lt_400_499;

/**
 * [400] Nth Digit
 */
public class LC_400 {
    // 正向查找ith个digit
    private int find(int val, int ith) {
        //System.out.println(val);
        int ans = 0;
        while (ith-- > 0) {
            ans = val % 10;
            val /= 10;
        }
        return ans;
    }
    /**
     * 189 -> 99的第二位-> 9
     *
     * 189 - 9 = 190
     * 第二轮
     * carry表示10(两位数)
     * i记录位数
     * 那么第i轮的总数字数为9*i*carry
     *
     * 如果n在本轮中出现，那么digit会出现在数字[carry+(n-1)/i]中
     * 位数为(n-1)%i (按照起始0开始计算)
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int i = 1;
        int carry = 1;
        while (n > 0) {
            long total = 9L * carry * i;    // i位数一共能有多少个digit
            if (n > total) {
                n-= total;
                i++;
                carry*=10;
            } else {
                int number = carry + (n-1)/i;   // 定位到number
                int ith = (n-1)%i;              // 定位number中的第几个digit
                return find(number, i-ith); // 找到number中第(i-ith)位的digit
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 11;
        LC_400 lc_400 = new LC_400();
        System.out.println(lc_400.findNthDigit(n));
    }
}
