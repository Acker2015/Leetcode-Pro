package lt_300_399;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [350] Intersection of Two Arrays II
 */
public class LC_350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] ret = new int[list.size()];
        int idx = 0;
        for (Integer val: list) {
            ret[idx++] = val;
        }
        return ret;
    }
}
