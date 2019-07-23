package lt_300_399;

/**
 * [390] Elimination Game
 */
public class LC_390 {
    /**
     * the idea is to record head value in each turn, when the total number left is 1. the left value would be the answer.
     *
     * 记住每次eliminate的起始值和相邻两个数的距离
     * 1. 相邻两个数的距离distance默认为1，后边每一次eliminate的时候距离翻倍
     * 2. 如何改变起始位置start？
     *      2.1 如果是正向消除，那么起始位置start肯定会被eliminate，更新start=start+distance
     *      2.2 如果是逆向消除，那么起始位置是否被eliminate取决于本轮参与game的元素个数
     *          2.2.1 如果元素个数为偶数，那么起始位置不变，因为不会被eliminate
     *          2.2.2 如果元素个数为奇数，那么起始位置改变为第二个数，start=start+distance
     */
    public int lastRemaining(int n) {
        boolean headToTail = true;
        int start = 1, distance = 1;
        while (n > 1) {
            if (headToTail) {
                start += distance;
            } else {
                if (n % 2 == 1) {
                    start += distance;
                }
            }
            n /= 2;
            distance *= 2;
            headToTail = !headToTail;
        }
        return start;
    }
}
