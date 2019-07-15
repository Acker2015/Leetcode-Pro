package other.O1Point3Acre.ms;

/**
 * Guests from all over the world are arriving at the local airport to attend the convention.
 * Specifically, there are N guests arriving at the airport and guest i arrives at time ti.
 * You have arranged M buses to transport the cows from the airport.Each bus can hold up to C guests in it.
 * You are waiting with the buses at the airport and would like to assign the arriving guests to the buses.
 * A bus can leave at the time when the last guest on it arrives.
 * You want to be a good host and so do not want to keep the arriving guests waiting at the airport too long.
 * What is the smallest possible value of the maximum waiting time of any one arriving guest if you coordinate your buses optimally?
 * A guest’s waiting time is the difference between her arrival time and the departure of her assigned bus.
 * It is guaranteed that MC≥N.
 *
     SAMPLE:
     N = 6 M= 3 C= 2
     1 1 10 14 4 3
     SAMPLE OUTPUT:
     4
 If the two guests arriving at time 1 go in one bus, guests arriving at times 3 and 4 in the second, and guests arriving at times 10 and 14 in the third,
 the longest time a guest has to wait is 4 time units (the guest arriving at time 10 waits from time 10 to time 14).

 https://www.1point3acres.com/bbs/thread-536422-1-1.html

 */
public class M_010 {

    private static int maxminTime;
    private static int N, M, C;
    public static void backTracking(int[] times, int idx, int busIdx, int guestNum, int maxWaitTime, int beginTime) {
        if (busIdx > M || guestNum > C) return;
        if (idx >= N) {
            maxminTime = Math.min(maxWaitTime, maxminTime);
        } else {
            // 这里加一步贪心，如果本乘客不换车，并且等待时间不超过之前的最长时间，那么直接等待即可
            if (times[idx] - beginTime <= maxWaitTime && guestNum+1 <= C) {
                backTracking(times, idx+1, busIdx, guestNum+1, maxWaitTime, beginTime);
            } else {
                int newMaxTime = Math.max(maxWaitTime, times[idx] - beginTime);
                backTracking(times, idx+1, busIdx, guestNum+1, newMaxTime, beginTime);
                // 剪枝 - 换车 - 考虑剩下的车能否装下剩下的人
                if (times.length-idx <= C * (M - busIdx)) {
                    backTracking(times, idx+1, busIdx+1, 1, maxWaitTime, times[idx]);
                }
            }

        }
    }
    public static int minmaxWaitTime(int NN, int MM, int CC, int[] times) {
        maxminTime = Integer.MAX_VALUE;
        N = NN; M = MM; C = CC;
        backTracking(times, 1, 1, 1, 0, times[0]);
        return maxminTime;
    }

    public static void main(String ...args) {
        int M = 3, C = 3;
        int [] times = new int[] {1,1,3,4,10,18,20,25};
        System.out.println(minmaxWaitTime(times.length, M, C, times));
    }
}
