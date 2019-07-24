package lt_1100_1199;

/**
 * 曼哈顿距离
 * Given two arrays of integers with equal lengths, return the maximum value of:
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 */
public class LC_1131 {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int len = arr1.length;
        if (len <= 0) return 0;
        int maxAbsVal = 0;
        for (int i = -1; i <= 1; i+=2) {
            for (int j = -1; j <= 1; j+=2) {
                int min = arr1[0]*i + arr2[0]*j;
                int max = arr1[0]*i + arr2[0]*j;
                for (int k = 0; k < len; ++k) {
                    int tmp = arr1[k]*i + arr2[k]*j + k;
                    min = Math.min(min, tmp);
                    max = Math.max(max, tmp);
                }
                maxAbsVal = Math.max(maxAbsVal, max-min);
            }
        }
        return maxAbsVal;
    }

    public static void main(String ...args) {
        int[] arr1 = {1,2,3,4};
        int[] arr2 = {-1,4,5,6};
        System.out.println(new LC_1131().maxAbsValExpr(arr1, arr2));
    }
}
