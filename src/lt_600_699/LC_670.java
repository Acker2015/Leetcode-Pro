package lt_600_699;

/**
 * [670] Maximum Swap
 */
public class LC_670 {
    /**
     * Greedy
     * 存储num中各个数字对应的下标，如果有重复digit，那么存储索引位置数字低位的索引{比如97808},存储8的索引为4而不是2,因为这样交换的数更大
     * 所以从高位开始遍历，每次都在之后的位置查找比它更大的digit来交换
     * 交换发生最多一次
     *
     * exp:
     * 首位如果不是最大digit，那么要跟后边位置尽量低的最大digit互换
     * 926808
     * 这里2需要跟最后一个8交换，而不是跟前边的8换,得到986802
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        char[] numArr = String.valueOf(num).toCharArray();
        int[] idxMem = new int[10];
        for (int i = 0; i < numArr.length; ++i) {
            idxMem[numArr[i]-'0'] = i;
        }
        boolean flag = false; // 是否已经变化
        for (int i = 0; i < numArr.length && !flag; ++i) {
            for (int d = 9; d > numArr[i]-'0'; --d) {
                if (idxMem[d] > i) {
                    int j = idxMem[d];
                    char ans = numArr[i];
                    numArr[i] = numArr[j];
                    numArr[j] = ans;
                    flag = true;
                    break;
                }
            }
        }
        return Integer.parseInt(String.valueOf(numArr));
    }
}
