package other.O1Point3Acre.ms;

/**
 *
 * 如果num的bit位为1位置，如果在target对应位置bit位不为1，那么这个num不被考虑计算，因为这个数不会参与或运算
 * 1. 如果num的bit位为1位置，如果在target对应位置bit位不为1，那么这个num不被考虑计算。所以只考虑被target的bit位包含的num，因为不包含的只要参与或操作结果都不会等于target
 * 2. 对于符合条件的num，记录对应bit位出现的num个数
 * 3. 输出target比特位为1，并且对应num个数最小的。这就是需要删除的最小的num个数
 *
 */
public class M_007_delete_for_bit_or {
    private boolean targetBitContain(int num, boolean[] targetBit) {
        for (int i = 0; i < 32; ++i) {
            boolean ans = (num&1) > 0;
            if (ans && !targetBit[i]) {
                return false;
            }
            num >>= 1;
        }
        return true;
    }

    public int deleteNum(int[] nums, int target) {
        boolean[] targetBit = new boolean[32];
        int[] ans = new int[32];
        int temp = target;
        for (int i = 0; i < 32; ++i) {
            targetBit[i] = (temp&1)>0;
            temp >>= 1;
        }
        for (int num: nums) {
            if (!targetBitContain(num, targetBit)) continue;
            temp = num;
            for (int i = 0; i < 32; ++i) {
                ans[i] += (temp&1);
                temp >>= 1;
            }
        }
        int mindeleteNum = nums.length+1;
        for (int i = 0; i < 32; ++i) {
            if (targetBit[i]) {
                mindeleteNum = Math.min(mindeleteNum, ans[i]);
            }
        }
        return mindeleteNum == nums.length+1 ? 0 : mindeleteNum;
    }

    public static void main(String ...args) {
        int[] nums = {9, 1, 3, 8, 4, 5};
        M_007_delete_for_bit_or solution = new M_007_delete_for_bit_or();
        System.out.println(solution.deleteNum(nums, 7));
    }
}
