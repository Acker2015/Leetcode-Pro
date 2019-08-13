package lt_1100_1199;

/**
 *
 */
public class LC_1144 {
    public class Solution1 {
        // 偶数位较大 - 那么如果奇数位比两边大，就需要decrease，降到刚好比两边较小值还小1就ok
        private int evenGreater(int[] nums) {
            int cnt = 0;
            for (int i = 1; i < nums.length; i+=2) {
                int left = nums[i-1];
                int right = i+1<nums.length ? nums[i+1] : Integer.MAX_VALUE;
                cnt += Math.max(0, nums[i] - Math.min(left,right)+1);
            }
            return cnt;
        }
        // 奇数位较大 - 那么如果偶数位比两边的，就需要decrease
        private int oddGreater(int[] nums) {
            int cnt = 0;
            for (int i = 0; i < nums.length; i+=2) {
                int left = i-1>=0 ? nums[i-1]:Integer.MAX_VALUE;
                int right = i+1<nums.length ? nums[i+1]:Integer.MAX_VALUE;
                cnt += Math.max(0, nums[i]-Math.min(left, right)+1);
            }
            return cnt;
        }
        /**
         两种方案各走一边，让大的数变小

         比如 abc 需要组织成a > b && b < c 的形式，如果b较大，那么就通过缩小b来达到目的
         缩小到 min(a,c)-1
         缩小的距离为 b-min(a, c)+1

         当然如果一开始b就满足a>b&&b<c, 那么缩小的距离b-min(a, c)+1就是负数
         所以最终缩小的距离就是max(0, b-min(a, c)+1)
         */
        public int movesToMakeZigzag1(int[] nums) {
            return Math.min(evenGreater(nums), oddGreater(nums));
        }
    }

    public class Solution2 {
        // one-pass cnt[0]代表nums中奇数大的zigzag，cnt[1]代表nums中偶数大的zigzag
        public int movesToMakeZigzag(int[] nums) {
            int[] cnt = new int[2];
            int len = nums.length;
            // 将最左最右补上一个大数
            int[] numsCopy = new int[len+2];
            numsCopy[0] = numsCopy[len+1] = Integer.MAX_VALUE;
            for (int i = 0; i < len; ++i) {
                numsCopy[i+1] = nums[i];
            }
            for (int i = 1; i < len+1; ++i) {
                cnt[i%2] += Math.max(0, numsCopy[i]-Math.min(numsCopy[i-1],numsCopy[i+1])+1);
            }
            return Math.min(cnt[0], cnt[1]);
        }
    }
}
