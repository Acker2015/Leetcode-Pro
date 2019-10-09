package lt_200_299;


public class LC_260 {
    public int[] singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            ans ^= nums[i];
        }
        // 获取二进制ans的最后一个1
        int flag = ans&(-ans);
        int ans1 = 0, ans2 = 0;
        for (int i = 0; i < nums.length; ++i) {
            if ((nums[i]&flag)>0) {
                ans1 ^= nums[i];
            } else {
                ans2 ^= nums[i];
            }
        }
        return new int[]{ans1, ans2};
    }

    public static void main(String ...args) {
        int[] nums = {1,2,1,3,2,5};
        LC_260 lc_260 = new LC_260();
        int[] result = lc_260.singleNumber(nums);
        for (int i = 0; i < result.length; ++i) {
            System.out.println(result[i] + " ");
        }
    }
}
