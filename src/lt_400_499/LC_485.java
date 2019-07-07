package lt_400_499;

/**
 * [485] Max Consecutive Ones
 */
public class LC_485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int mco = 0;
        int ans = 0;
        for (int num: nums) {
            if (num != 1) {
                ans = 0;
            } else {
                ans++;
                if (ans > mco) {
                    mco = ans;
                }
            }
        }
        return mco;
    }
}
