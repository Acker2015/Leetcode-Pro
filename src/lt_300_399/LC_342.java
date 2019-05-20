package lt_300_399;

/**
 * [342] Power of Four
 */
public class LC_342 {
    /**
     * solution1
     *
     * recursive
     * @param num
     * @return
     */
    public boolean isPowerOfFour1(int num) {
        if (num == 0) return false;
        if (num == 1) return true;
        return num%4==0 && isPowerOfFour(num/4);
    }
    /**
     * solution2
     *
     * bit maniplution
     * 只有一个bit为1， 并且bit位为偶数(0[1], 2[4], 4[16], 6[32], ...)
     *
     */
    public boolean isPowerOfFour2(int num) {
        for (int i = 0; i < 32; i+=2) {
            if (num == 1) return true;
            if ((num & 3) > 0) return false;
            num >>= 2;
        }
        return false;
    }

    /**
     * solution3
     *
     * 4的次幂肯定值偶数bit位为1， 并且二进制只有一个bit为1
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
    }
}
