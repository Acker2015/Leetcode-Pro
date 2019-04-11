package lt_300_399;

import javafx.util.Pair;

/**
 *
 *
 * [396] Rotate Function
 *
 * Example:
 *
 * A = [4, 3, 2, 6]
 *
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25   -> give up 4
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16    -> 4,3,2 give up 6
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23    -> 6,4,3 give up 2
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26  -> give up 3
 *
 * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 *
 *  x = A[len-1-k]
 *  计算F(k+1)时候，前(n-1)个数都向后滑动一位,因为旋转的时候索引都+1，
 *  而最后一个数的索引会因为旋转而变成0，所以求F(k+1)的时候，需要将最后一个数的值减去
 *
 *  F(k+1) = F(k) - (len-1)*x + (sum-x)
 *  其中 x = A[len-1-k],表示F(k)这轮的最后一个数
 *
 *  1. (len-1)*x表示在下一轮x索引变为0，所以将这部分减掉
 *  2. 前(n-1)个数因为旋转索引都后移一位，索引前(n-1)的和需要增加(sum-x)
 *
 *  F(k+1) = F(k)-(len-1)*x + (sum -x)
 *
 *  https://leetcode.com/problems/rotate-function/discuss/87853/Java-O(n)-solution-with-explanation
 */
public class LC_396 {
    public int maxRotateFunction(int[] A) {
        int sum = 0, fPre = 0;
        int len = A.length;
        for (int i = 0; i < len; ++i) {
            sum += A[i];
            fPre += A[i]*i;
        }
        int maxF = fPre;
        for (int i = 1; i < A.length; ++i) {
            int x = A[len-i];
            int fNext = fPre + sum - len*x;
            maxF = Math.max(fNext, maxF);
            fPre = fNext;
        }
        return maxF;
    }

    public static void main(String ...args) {
        int[] nums={4, 3, 2, 6};
        LC_396 lc_396 = new LC_396();
        System.out.println(lc_396.maxRotateFunction(nums));
    }
}
