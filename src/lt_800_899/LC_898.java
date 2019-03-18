package lt_800_899;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/18
 */
public class LC_898 {
    /**
     * 此题方法很straight forward，但是时间复杂度的分析稍微有点难度
     *
     Time Complexity:
     O(30N)

     Normally this part is easy.
     But for this problem, time complexity matters a lot.

     The solution is straight forward,
     while you may worry about the time complexity up to O(N^2)
     However, it's not the fact.
     This solution has only O(30N)

     The reason is that, B[0][i] >= B[1][i] >= ... >= B[i][i].
     B[0][i] covers all bits of B[1][i]
     B[1][i] covers all bits of B[2][i]
     ....

     There are at most 30 bits for a positive number 0 <= A[i] <= 10^9.
     So there are at most 30 different values for B[0][i], B[1][i], B[2][i], ..., B[i][i].
     Finally cur.size() <= 30 and res.size() <= 30 * A.length()

     B[i][i] -> B[0][i]单调递增，只能说明二进制中1的个数越来越多，并且没有重复的数字，因为bit位最多30位
     所以cur中最多30个元素，故时间复杂度为O(30N)

     In a worst case, A = {1,2,4,8,16,..., 2 ^ 29}
     And all B[i][j] are different and res.size() == 30 * A.length()
     * @param A
     * @return
     */
    public int subarrayBitwiseORs(int[] A) {
        if (A.length <= 1) return A.length;
        Set<Integer> res = new HashSet<>(), cur = new HashSet<>();
        for (Integer Ai: A) {
            Set<Integer> tmp = new HashSet<>();
            for (Integer v: cur) {
                tmp.add(v|Ai);
            }
            tmp.add(Ai);
            res.addAll(tmp);
            cur = tmp;
        }
        return res.size();
    }
}
