package lt_400_499;

/**
 * [413] Arithmetic Slices
 * two pointers easy
 */
public class LC_413 {
    public int numberOfArithmeticSlices(int[] A) {
        int i = 0, j = 1;
        int sum = 0;
        while (j < A.length) {
            while (j + 1 < A.length && A[j+1]-A[j]==A[j]-A[j-1]) {
                j++;
            }
            int sliceLen = j-i+1;
            if (sliceLen >= 3) {
                // 1+2+...+(sliceLen-2)=(sliceLen-1)*(sliceLen-2)/2
                sum += (sliceLen-2)*(sliceLen-1)/2;
            }
            i = j;
            j++;
        }
        return sum;
    }
}
