package lt_200_299;


import java.util.ArrayList;
import java.util.List;

/**
 * [228] Summary Ranges
 * Array
 */
public class LC_228 {
    private String buildSummary(int start, int end) {
        if (start == end) {
            return String.valueOf(start);
        } else {
            return String.valueOf(start) + "->" + String.valueOf(end);
        }
    }
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums.length <= 0) return list;
        int i = 1;
        int start = nums[0], end = nums[0];
        while (i < nums.length) {
            if (nums[i] == end + 1) {
                end++;
            } else {
                list.add(buildSummary(start, end));
                start = end = nums[i];
            }
            i++;
        }
        list.add(buildSummary(start, end));
        return list;
    }
}
