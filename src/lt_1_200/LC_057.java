package lt_1_200;


import java.util.ArrayList;
import java.util.List;

/**
 * [57] Insert Interval
 * greedy
 */
public class LC_057 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // inserted means if the newInterval has been inserted into result.
        boolean inserted = false;
        List<int[]> list = new ArrayList<>();
        int left = newInterval[0], right = newInterval[1];
        for (int[] inv: intervals) {
            if (inserted) {
                list.add(inv);
                continue;
            }
            if (inv[1] < left) {
                list.add(inv);
            } else if (inv[0] > right) {
                inserted = true;
                list.add(new int[]{left, right});
                list.add(inv);
            } else {
                left = Math.min(left, inv[0]);
                right = Math.max(right, inv[1]);
            }
        }
        // case, the interval may be inserted into the last.
        if (!inserted) {
            list.add(new int[]{left, right});
        }
        return list.toArray(new int[][]{});
    }

    public static void main(String ...args) {
        int[][] arr = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] interval = {4,8};
        int[][] ret = new LC_057().insert(arr, interval);
        for (int[] sub: ret) {
            System.out.println(sub[0] + " - " + sub[1]);
        }
    }
}
