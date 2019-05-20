package lt_300_399;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * [349] Intersection of Two Arrays
 * two pointers
 */
public class LC_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        Set<Integer> set = new HashSet<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] ret = new int[set.size()];
        int idx = 0;
        for (Integer val: set) {
            ret[idx++] = val;
        }
        return ret;
    }
}
