package basic_data_structure.sort;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/31
 */
public class QuickSort {
    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    /**
     * 每个partition能够保证一个元素落到了正确的位置上
     *
     * O(nlogn)
     *
     * @param A
     * @param left
     * @param right
     */
    public void partition(int[] A, int left, int right) {
        if (left >= right) return;
        int cmpVal = A[left];
        int i = left, j = right;
        while (i < j) {
            while (A[j] > cmpVal && j > i) j--;
            swap(A, i, j);
            while (A[i] <= cmpVal && j > i) i++;
            swap(A, i, j);
        }
        A[i] = cmpVal;
        partition(A, left, i-1);
        partition(A, i+1, right);
    }

    public static void main(String ...args) {
        QuickSort quickSort = new QuickSort();
        int[] A = {66, 12, 33, 57, 64, 27, 18};

        quickSort.partition(A, 0, A.length-1);
        for (int i = 0; i < A.length; ++i) {
            System.out.print(A[i] + " ");
        }
    }
}
