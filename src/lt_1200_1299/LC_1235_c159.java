package lt_1200_1299;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1235. Maximum Profit in Job Scheduling
 *
 * 按照结束时间排序
 * dp[i]表示到第二个任务能够获得的最大收益
 * 1. 不选择第i个job，那么最大收益等于dp[i-1]
 * 2. 选择第i个job，那么dp[i] = Math.max(dp[i], dp[j]+profit[i])
 *      其中job_j的结束时间小于等于job_i的开始时间 (这里可以选择二分)
 *
 * O(nlogn)
 *
 *
 * 当然这里可以按照开始时间排序，这样就需要从后往前遍历，二分寻找后边开始时间大于等于当前结束时间的第一个job
 */
public class LC_1235_c159 {
    private class Job {
        int start, end, profit;
        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    private int binarySearch(Job[] jobs, int time) {
        int l = 0, r = jobs.length, mid;
        while (l < r) {
            mid = l + (r-l)/2;
            if (jobs[mid].end <= time) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l-1;
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        if (len <= 0) return 0;
        Job[] jobs = new Job[len];
        for (int i = 0 ; i < len; ++i) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparing(j -> j.end));
        int[] dp = new int[len];
        dp[0] = jobs[0].profit;
        int maxProfit = dp[0];
        for (int i = 1; i < len; ++i) {
            dp[i] = Math.max(dp[i-1], jobs[i].profit);
//            for (int j = i-1; j >= 0; --j) {
//                if (jobs[j].end <= jobs[i].start) {
//                    dp[i] = Math.max(dp[i], dp[j]+jobs[i].profit);
//                    break;
//                }
//            }
            // 找到之前end时间刚好满足小于等于start时间的第一个job
            int j = binarySearch(jobs, jobs[i].start);
            if (j >= 0) {
                dp[i] = Math.max(dp[i], dp[j]+jobs[i].profit);
            }
            maxProfit = Math.max(maxProfit, dp[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] startTime = {1,2,3,4,6};
        int[] endTime = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        LC_1235_c159 solution = new LC_1235_c159();
        System.out.println(solution.jobScheduling(startTime, endTime, profit));
    }
}
