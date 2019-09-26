package lt_1_200;

/**
 * [134] Gas Station
 *
 * 如果起始点是i，那么如果i->j的时候，如果油不够，那么[i,j]之间任何一个都不会作为起始点
 * 因为能够走到j，说明走过之前任一个点后，还剩余油
 *
 * 所以要么在i之前找起始点，要么在j之后找起始点
 *
 * solution1尝试i之前找起始点的解法
 * solution2尝试j之后找起始点的解法
 *
 *
 */
public class LC_134 {
    /**
     * solution1
     * 假设end为0， start为gas.length-1
     * 初始化sum = gas[start]-cost[start]
     * 1. 如果sum>0，说明可以到达end
     * 2. 否则sum<0说明，从start-end之间的任何位置都到不了end，只能向前寻找触发点start--
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int start = gas.length-1;
        int end = 0;
        int sum = gas[start]-cost[start];
        while (end < start) {
            // travel to end
            if (sum > 0) {
                sum += (gas[end] - cost[end]);
                end++;
            } else {
                start--;
                sum += (gas[start]-cost[start]);
            }
        }
        return sum >= 0 ? start : -1;
    }

    /**
     * tank记录从起点start到临时终点i的油量
     * 如果tank<0，显然start->i之间没有一个可以作为起始点，那么选择起始点为i+1,并将rank重置
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; ++i) {
            sum += (gas[i]-cost[i]);
            tank += (gas[i]-cost[i]); // 出发到i需要的剩余
            // 如果剩余tank<0，说明到不了i， 那么tank置为0，起点置为i+1
            if (tank < 0) {
                tank = 0;
                start = i+1;
            }
        }
        return sum >= 0 ? start%gas.length:-1;
    }
}
