package lt_300_399;


/**
 * [367] Valid Perfect Square
 */
public class LC_367 {
    /**
     * binary-search
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) return true;
        int l = 0, r = num-1, mid;
        while(l <= r) {
            mid = l + (r-l)/2;
            long ans = Long.valueOf(mid)*mid;
            if (ans == num) {
                return true;
            } else if (ans < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
