package other.O1Point3Acre.ms;

import javafx.util.Pair;

/**
 * https://www.techiedelight.com/find-pair-array-minimum-absolute-sum/
 *
 * Find pair in an array hiving minimum absolute sum
 *
 * Given a sorted array of integers, find a pair in it having minimum absolute sum
 *
 * Example
 * input: A=[-6, -5, -3, 0, 2, 4, 9]
 * ouput: pair is (-5, 4)
 *
 * solution: two pointers
 */
public class M_001_FindMinAbsoluteSum {
    public static Pair<Integer, Integer> findPair(int[] A)  {
        if (A.length < 2) {
            return null;
        }
        int maxSum = Integer.MAX_VALUE;
        int i = 0, j = A.length-1;
        int low = i, high = j;
        while (i < j) {
            int ans = Math.abs(A[i] + A[j]);
            if (ans < maxSum) {
                maxSum = ans;
                low = i;
                high = j;
                if (maxSum == 0) break;
            }
            if (A[i] + A[j] > 0) {
                j--;
            } else {
                i++;
            }
        }
        System.out.println("left:" + low + ", right:" + high);
        return new Pair<>(low, high);
    }

    public static void main(String ...args) {
        int[] A = {-6, -5, -3, 0, 2, 4, 9};
        findPair(A);
    }
}
