package lt_1_200;


public class LC_190 {
    // you need treat n as an unsigned value
    public int reverseBits1(int n) {
        int tmp = 0;
        for (int i = 0; i < 32; ++i) {
            tmp = tmp*2 + (n&1);
            n = n >> 1;
        }
        return tmp;
    }
    /**
     *
     * [190] Reverse Bits
     *
     * 16 -> 8 -> 4 -> 2 -> 1
     * 使用无符号的偏移
     */
    public int reverseBits(int n) {
        // 高低16位交换
        n = (n<<16) | (n>>>16);
        n = ((n&0xff00ff00)>>>8) | ((n&0x00ff00ff)<<8);
        n = ((n&0xf0f0f0f0)>>>4) | ((n&0x0f0f0f0f)<<4);
        n = ((n&0xcccccccc)>>>2) | ((n&0x33333333)<<2);
        n = ((n&0xaaaaaaaa)>>>1) | ((n&0x55555555)<<1);
        return n;
    }

    public static void main(String ...args) {
        int val = -3;
        LC_190 lc_190 = new LC_190();
        System.out.println(lc_190.reverseBits(val));
    }
}
