package other.O1Point3Acre.ms;

/**
 * Given a sorted of integers, find a pair in it having minimum absolute sum
 *
 * Solution: two pointers
 *
 * the idea is to maintain search space by maintaining two indexes(low and high) that initially points to two end-points
 * of the array. Then we loop util low id less than high index and reduce the search space in [low, high]
 * at each iteration of the loop by comparing sum of elements present at index low and high with 0.
 * we increase the low index if the sum is less than 0 because we should make the sum bigger, so the lower index should increase
 * we decrease the high index if the sum if bigger than 0 because we should make the sum smaller, so the high index should decrease.
 */
public class M_008_findPair {
    public int[] findPair(int[] A) {
        int i = -1, j = -1, minSum = Integer.MAX_VALUE;
        if (A.length < 2) {
            return new int[]{i,j};
        }
        int low = 0, high = A.length-1;

        while (low < high) {
            int sum = A[low] + A[high];
            if (Math.abs(sum) < minSum) {
                i = low;
                j = high;
                minSum = Math.abs(sum);
            }
            if (sum == 0) break;
            if (sum < 0) {
                low++;
            } else {
                high--;
            }
        }
        return new int[]{i,j};
    }

    public static void main(String... args) {
        int[] A = {-6,-5,-3,0,2,4,9};
        M_008_findPair m_008_findPair = new M_008_findPair();
        int[] ret = m_008_findPair.findPair(A);
        System.out.println("solution: " + ret[0] + ", " + ret[1]);
    }
}
