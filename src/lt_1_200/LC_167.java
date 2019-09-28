package lt_1_200;

/**
 * [167] Two Sum II - Input array is sorted
 *
 * two-pointers
 */
public class LC_167 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
        int i = 0, j = numbers.length-1;
        while (i < j) {
            int ans = numbers[i]+numbers[j];
            if (ans == target) {
                ret[0] = i+1;
                ret[1] = j+1;
                return ret;
            } else if (ans < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }
}
