package lt_1100_1199;

/**
 * 1122. Relative Sort Array
 */
public class LC_1122 {
    /*
    桶排序
    */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] mem = new int[1001];
        for (int num: arr1) {
            mem[num]++;
        }
        int idx = 0;
        for (int num: arr2) {
            while (mem[num]-- > 0) {
                arr1[idx++] = num;
            }
        }
        for (int i = 0; i < 1001; ++i) {
            while (mem[i]-- > 0) {
                arr1[idx++] = i;
            }
        }
        return arr1;
    }
}
