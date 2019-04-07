package lt_1_200;


public class LC_137 {
    public int singleNumber1(int[] nums) {
        int[] mask = new int[32];
        for (int i = 0; i < nums.length; ++i) {
            int tmp = nums[i];
            for (int j = 0; j < 32; ++j) {
                if (((tmp>>j)&1) ==1) {
                    mask[j] += 1;
                    mask[j] %= 3;
                }

            }
        }
        int ret = 0;
        for (int i = 0; i < 32; ++i) {
            if (mask[i]>1) {
                ret += (1<<i);
            }
        }
        return ret;
    }
    /**
     * ones   代表第ith 位只出现一次的掩码变量
     * twos  代表第ith 位只出现两次次的掩码变量
     * threes  代表第ith 位只出现三次的掩码变量
     */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < nums.length; ++i) {
            twos |= (ones&nums[i]);
            ones ^= nums[i];
            threes = twos&ones;
            ones = ones & (~threes);
            twos = twos & (~threes);
        }
        return ones;
    }

    public static void main(String ...args) {
        int[] nums = {0,1,0,1,0,1,99};
        LC_137 lc_137 = new LC_137();
        System.out.println(lc_137.singleNumber(nums));
    }
}
