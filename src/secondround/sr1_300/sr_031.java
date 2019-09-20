package secondround.sr1_300;

import java.util.Arrays;
import java.util.stream.Stream;

public class sr_031 {
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;
        int j = nums.length-1;
        while (j > 0 && nums[j-1] >= nums[j]) j--;
        if (j == 0) {
            Arrays.sort(nums);
        } else {
            // 选择右边降序序列中比nums[j-1]大的最小值，与nums[j-1]交换，然后将nums[j...]排序为升序即可
            int i = j;
            while (i+1 < nums.length && nums[i+1] > nums[j-1]) i++;
            swap(nums, j-1, i);
            Arrays.sort(nums, j, nums.length);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,4,3,1};
        new sr_031().nextPermutation(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
