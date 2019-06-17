package other.O1Point3Acre.ms;

/**
 * 给定一个有序的正整数数组，在首尾之间，不连续的部分可以看成是漏掉了一些数。这些漏掉的数可以组成一个虚拟的数组.
 * 要求给出一个序号K，返回虚拟数组的第K个数
 *
 * example
 * 比如给定的原数组:[2,4,7,8,9,15],漏掉的数组成这样一个虚拟数组:[3,5,6,10,11,12,13,14]
 * 如果K=2，则返回的虚拟数组的第二个数就是5
 *
 * 此题意在一个数组中用二分法找找不到的数，了解此题，可以拓宽对二分的了解
 */
public class M_005_binarySearch {
    private int num_missing(int[] nums, int left, int right) {
        return nums[right] - nums[left] - (right-left);
    }
    private int k_missing_num(int[] nums, int k) {
        int missed = num_missing(nums, 0, nums.length-1);
        if (missed < k) {
            return Integer.MIN_VALUE;
        }
        int left = 0, right = nums.length-1, mid;
        // 注意边界条件
        while (left+1 < right) {
            mid = left + (right-left)/2;
            missed = num_missing(nums, left, mid);
            if (missed >= k) {
                right= mid;
            } else {
                left = mid;
                k = k-missed;
            }
        }
        return nums[left] + k;
    }

    public static void main(String ...args) {
        M_005_binarySearch solution = new M_005_binarySearch();
        int[] nums = {2,4,7,8,9,15};
        int[] missings = {3,5,6,10,11,12,13,14};
        for (int i = 0; i < missings.length; ++i) {
            if (missings[i] != solution.k_missing_num(nums, i+1)) {
                System.out.println("loc="+i);
            }
        }
    }
}
