package basic_data_structure.sort;

import java.util.Arrays;

/**
 * 二路归并排序
 */
public class MergeSort {
    private final static Integer MAXN = 100;

    public void merge(int[] A, int left, int mid, int right) {
        int i = left, j = mid+1;
        int[] tmp = new int[right-left+1];
        int idx = 0;
        while (i <= mid && j <= right) {
            if (A[i] <= A[j]) {
                tmp[idx++] = A[i++];
            } else {
                tmp[idx++] = A[j++];
            }
        }
        while (i <= mid) tmp[idx++] = A[i++];
        while (j <= right) tmp[idx++] = A[j++];
        for (i = 0; i < idx; ++i) {
            A[left+i] = tmp[i];
        }
    }

    /**
     * recursion implementation
     * @param A
     * @param left
     * @param right
     */
    public void mergeSort(int[] A, int left, int right) {
        // 中间结果是 左半边先排好序，然后右半边在排好序
        if (left < right) {
            int mid = left + (right-left)/2;
            mergeSort(A, left, mid);
            mergeSort(A, mid+1, right);
            merge(A, left, mid, right);
        }
    }

    /**
     * circulation implementation
     * @param A
     */
    public void mergeSort(int[] A) {
        int n = A.length;
        // step表示组内元素个数，左右各step/2, 左一step最长为n/2向上取整
        for (int step = 2; step/2 <= n; step *= 2) {
            for (int i = 0; i < n; i+= step) {
                //Arrays.sort(A, i, Math.min(i + step, n));
                int mid = i + step/2 - 1;
                if (mid + 1 < n) {
                    merge(A, i, mid, Math.min(i+step-1, n-1));

                }
            }
            print(A);
        }
    }

    public void print(int[] A) {
        for (int i = 0; i < A.length; ++i) {
            System.out.print(A[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String ...args) {
        int[] A = {66, 12, 33, 57, 64, 27, 18};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(A);
    }


}
