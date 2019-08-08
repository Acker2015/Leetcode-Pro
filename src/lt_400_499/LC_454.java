package lt_400_499;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [454] 4Sum II
 */
public class LC_454 {
    /**
     * Solution1
     * O(N^3)
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        int len = A.length;
        int cnt = 0;
        if (len <= 0) return 0;
        Arrays.sort(C);
        Arrays.sort(D);
        Map<Integer, Integer> map = new HashMap<>();
        for (int val: D) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                for (int k = 0; k < len; ++k) {
                    int val = A[i]+B[j]+C[k];
                    cnt += map.getOrDefault(-val, 0);
                }
            }
        }
        return cnt;
    }

    /**
     * Solution2
     * O(n^2)
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int len = A.length;
        if (len <= 0) {
            return 0;
        }
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int v1: A) {
            for (int v2: B) {
                map.put(v1+v2, map.getOrDefault(v1+v2, 0) + 1);
            }
        }
        for (int v1: C) {
            for (int v2: D) {
                cnt += map.getOrDefault(-(v1+v2), 0);
            }
        }
        return cnt;
    }
}
