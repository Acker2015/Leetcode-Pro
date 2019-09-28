package lt_1_200;


public class LC_137 {
    /**
     * 使用32个整数表示每个bit位置，如果某个bit位置出现大于等于三次就需要对3取余
     * @param nums
     * @return
     */
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

    public int singleNumber2(int[] nums) {
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

    /**
     * ones   代表第ith 位只出现一次的掩码变量
     * twos  代表第ith 位只出现两次次的掩码变量
     * threes  代表第ith 位只出现三次的掩码变量
     */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < nums.length; ++i) {
            threes = twos&nums[i];  // three只有可能有出现两次的bit和新的nums求与组成
            twos = (~threes)&(twos | (ones&nums[i])); // twos可通过ones晋升，并且需要去掉自身晋升到threes的bit
            ones = (~threes)&(ones ^ nums[i]);  // ones可通过nums[i]^ones生成(去掉晋升到2的)，同时去掉nums[i]直接与twos相同bit晋升三的bit位
        }
        return ones;
    }



    public static void main(String ...args) {
        int[] nums = {0,1,0,1,0,1,99};
        LC_137 lc_137 = new LC_137();
        System.out.println(lc_137.singleNumber(nums));
    }
}
