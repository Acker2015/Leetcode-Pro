package other.O1Point3Acre.ms;


public class M_006AlmostSortedArray {
    // swap操作
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    // 判断序列是否有序
    private boolean judge(int[] arr, int left, int right) {
        int i = left+1;
        while (i <= right && arr[i] >= arr[i-1]) i++;
        return i > right;
    }

    /**
     * 判断left和right是否为降序组数组
     * 注意当right-left+1 <= 3 的时候优先使用swap，所以返回false
     * 如 5,4,3
     *      可以reverse也可以swap(5,3),这时候返回false，优先使用swap
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private boolean isSubDecrease(int[] arr, int left, int right) {
        int i = left + 1;
        while (i <= right && arr[i-1] >= arr[i]) i++;
        return i>right && (right-left > 2);
    }

    /**
     * 1. 从左往右找到第一个位置i，使得array[i] > array[i+1] （降序点）
     * 2. 从右往左找到第一个位置j，使得array[j] < array[j-1]  (左->右方向的降序点)
     * 判断在i,j之间用什么操作即可
     * @param array
     * @return
     */
    public boolean almostSortedArray(int[] array) {
        int len = array.length;
        // 原数组的有序判断
        if (judge(array, 0, len-1)) return true;
        int i = 0, j = len-1;
        while (i+1 < len && array[i] <= array[i+1]) i++;
        while (j-1 >= 0 && array[j-1] <= array[j]) j--;
        if (isSubDecrease(array, i, j)) {
            System.out.println("REVERSE");
            // 直接判断两边
            return (i==0 || array[j] >= array[i-1]) && (j == len-1 || array[i] <= array[j+1]);
        } else {
            System.out.println("SWAP");
            swap(array, i, j);
            return judge(array, 0, len-1);
        }
    }
}
