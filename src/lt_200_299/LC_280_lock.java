package lt_200_299;

import java.util.Arrays;

/**
 * 280 - wiggle sort
 *
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class LC_280_lock {
    /**
     * Solution1
     * O(nlogn)
     *
     * 直接先排序，从索引2处依次跟之前元素交换，step每次递增2.构造出来wiggle的结构
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i+=2) {
            int temp = nums[i];
            nums[i] = nums[i-1];
            nums[i-1] = temp;
        }
    }

    /**
     * Solution2
     * O(n)
     * 根据题目要求的nums[0] <= nums[1] >= nums[2] <= nums[3]....，我们可以总结出如下规律：
     * 当i为奇数时，nums[i] >= nums[i - 1]
     * 当i为偶数时，nums[i] <= nums[i - 1]
     * 那么我们只要对每个数字，根据其奇偶性，跟其对应的条件比较，如果不符合就和前面的数交换位置即可
     *
     * 为什么交换可以? 比如现在在索引i处，并且索引i为奇数
     * 如果nums[i] < nums[i-1] 那么交换两个元素
     * 如果nums[i+1]>nums[i] 那么交换两个元素
     *
     * 这两个操作说明 nums[i]作为奇数位置，将会变成更大
     *
     * 比如 3  1  4  2
     * 索引分别为0,1,2,3
     * 那么当i为1的时候，发现nums[0]>nums[1],交换变成 1 3 4 2
     * i到下一个位置2，此时为偶数，发现nums[2]>nums[1], 再次交换变成 1 4 3 2
     * i到下一个位置3，此时为奇数，发现nums[3]<nums[2],再次交换变成 1 4 2 3
     *
     * 所以这一些列操作使得奇数索引处的元素大于两边的元素
     * 同理，使得偶数索引处的元素小于两边元素
     *
     *
     * @param nums
     */
    public void wiggleSort2(int[] nums) {
        if (nums.length <= 1) return;
        for (int i = 1; i < nums.length; ++i) {
            if ((i%2==1 && nums[i]<nums[i-1]) || (i%2==0 && nums[i]>nums[i-1])) {
                int tmp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = tmp;
            }
        }
    }




    public static void main(String[] args) {
        int[] nums = {1,2,5,6,4,3};
        LC_280_lock lc_280_lock = new LC_280_lock();
        lc_280_lock.wiggleSort2(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
