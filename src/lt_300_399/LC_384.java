package lt_300_399;

import java.util.Random;

/**
 * [384] Shuffle an Array
 */
public class LC_384 {
    int[] nums;
    Random random;
    public LC_384(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuNums = nums.clone();
        for (int i = 1; i < shuNums.length; ++i) {
            int idx = random.nextInt(i+1);
            swap(shuNums, i, idx);
        }
        return shuNums;
    }

    private void swap(int[] a, int i, int j) {
        int ans = a[i];
        a[i] = a[j];
        a[j] = ans;
    }
}
