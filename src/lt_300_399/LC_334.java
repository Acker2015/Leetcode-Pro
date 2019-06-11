package lt_300_399;

/**
 * [334] Increasing Triplet Subsequence
 */
public class LC_334 {
    /**
     * solution
     *
     * time O(N)
     *
     * ans1,ans2分别记录前边较小的数, n为记录数的个数取1或者2
     * 遍历数组,cur表示当前遍历到的数
     * 1. n==1
     *      1.1 IF(cur <= ans1), 那么更新ans1 (ans1=cur, n = 1)
     *      1.2 IF(cur > ans1), 那么设置ans2 (ans2=cur, n = 2)
     * 2. n==2
     *      2.1 IF(cur > ans2), 那么triplet出现，结果返回true
     *      2.2 ELSE IF(cur > ans1) 那么ans2更新为更优
     *      2.3 ELSE 此时cur <= ans1, 说明可以有更优的ans1,ans2出现，则通过遍历尝试去更新更优的ans1,ans2
     * @param nums
     * @return
     */
    public boolean increasingTriplet1(int[] nums) {
        if (nums.length < 3) return false;
        int i = 1, len = nums.length;
        int ans1 = nums[0], ans2 = nums[0], n = 1;
        while (i < len) {
            // n=1, 更新ans1或者更新ans2
            if (n == 1) {
                if (nums[i] <= ans1) {
                    ans1 = nums[i];
                } else {
                    ans2 = nums[i];
                    n++;
                }
                i++;
            } else {
                if (nums[i] > ans2) {
                    return true;
                } else if (nums[i] > ans1) {
                    ans2 = nums[i];
                    i++;
                } else {
                    // important, find new ans1,ans2
                    int tmp = nums[i];
                    int j = i+1;
                    while (j < len) {
                        if (nums[j] > ans2) {
                            return true;
                        }
                        if (nums[j] <= tmp) {
                            tmp = nums[j++];
                        } else {
                            ans1 = tmp;
                            ans2 = nums[j++];
                            n = 2;
                            break;
                        }
                    }
                    i = j;
                }
            }
        }
        return false;
    }

    /**
     * Solution2
     * small,big分别记录前两个较小的数
     * 遍历数组，如果num<=small, 更新small = num
     * else 如果num <= big, 更新big = num
     * else triplet出现
     *
     * 这里small, big记录的并不是最终的序列值
     * 后边一旦出现大于big的数，说明triplet出现
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) { small = n; } // update small if n is smaller than both
            else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }

    public static void main(String ...args) {
        LC_334 lc_334 = new LC_334();
        int[] nums = {0,4,2,1,0,-1,-3};
        System.out.println(lc_334.increasingTriplet(nums));
    }
}
