package lt_500_599;


import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LC_539 {
    private final int BASE = 60*24;

    private int getRelativeTime(String point) {
        String[] arr = point.split(":");
        return Integer.parseInt(arr[0])*60 + Integer.parseInt(arr[1]);
    }
    private int getMinDiff(int a, int b) {
        int tmp = Math.abs(a-b);
        return Math.min(tmp, BASE-tmp);
    }

    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() < 2) {
            return -1;
        }
        int len = timePoints.size();
        int[] ret = new int[len];
        for (int i = 0; i < len; ++i) {
            ret[i] = getRelativeTime(timePoints.get(i));
        }

        int minDifferent = BASE;
        Arrays.sort(ret);
        for(int i = 1; i < len; ++i) {
            minDifferent = Math.min(minDifferent, getMinDiff(ret[i-1], ret[i]));
            if (i > 1) {
                minDifferent = Math.min(minDifferent, getMinDiff(ret[0], ret[i]));
            }
            if (i < len-1) {
                minDifferent = Math.min(minDifferent, getMinDiff(ret[i], ret[len-1]));
            }
        }
        return minDifferent;
    }

    public static void main(String[] args) {
        LC_539 lc_539 = new LC_539();
        System.out.println(lc_539.findMinDifference(Arrays.asList("00:00","23:59","00:00")));
    }
}
