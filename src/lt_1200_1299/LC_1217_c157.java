package lt_1200_1299;

/**
 *
 * 1217. Play with Chips
 *
 * There are some chips, and the i-th chip is at position chips[i].
 You can perform any of the two following types of moves any number of times (possibly zero) on any chip:

 Move the i-th chip by 2 units to the left or to the right with a cost of 0.
 Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
 There can be two or more chips at the same position initially.

 Return the minimum cost needed to move all the chips to the same position (any position).



 Example 1:

 Input: chips = [1,2,3]
 Output: 1
 Explanation: Second chip will be moved to position 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
 Example 2:

 Input: chips = [2,2,2,3,3]
 Output: 2
 Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.


 Constraints:

 1 <= chips.length <= 100
 1 <= chips[i] <= 10^9
 */
public class LC_1217_c157 {
    /**
     * 经典模拟-思路转换
     *
     * 由于移动两位不需要花费，移动一位需要1个花费
     * 所以首先可以根据移动两位不要花费，将所有偶数位置移动到0, 将所有奇数位移动到1
     * 然后再将0或者1处位置chip个数较少的进行一位移动即可
     * 输出 min(odd, even)
     * @param chips
     * @return
     */
    public int minCostToMoveChips(int[] chips) {
        int odd = 0, even = 0;
        for (int chip: chips) {
            if (chip % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(odd, even);
    }
}
