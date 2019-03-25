package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/24
 */
public class LC_169 {
    /**
     * 投票原理
     *
     * 首先可以证明出现次数n/2以上的数最多只有一个。
     *
     * 记录出现投票次数，以及投票的值
     * 出现值一样的，次数+1， 不一样则次数-1
     * 次数为0的时候就更换投票的值
     *
     * 这样遍历一遍剩下的就是出现最多的一个数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int record = nums[0];
        int ans = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (ans == 0) {
                record = nums[i];
                ans = 1;
            } else if (nums[i] == record) {
                ans++;
            } else {
                ans--;
            }
        }
        return record;
    }
}
