package lt_300_399;

/**
 * [357] Count Numbers with Unique Digits
 */
public class LC_357 {
    public int search(long prev, boolean[] used, int left) {
        int cnt = 0;
        if (left < 0) {
            return 0;
        }
        cnt++;
        for (int i = 0; i < 10; ++i) {
            if (!used[i]) {
                used[i] = true;
                cnt += search(10*prev+i, used, left-1);
                used[i] = false;
            }
        }
        return cnt;
    }
    /**
     * solution1
     * 回溯 backtracking
     */
    public int countNumbersWithUniqueDigits1(int n) {
        if (n > 10) n=10;
        // x = 0
        if (n == 0) return 1;
        int cnt = 1;
        boolean[] used = new boolean[10];
        for (int i = 1; i < 10; ++i) {
            used[i] = true;
            cnt += search(i, used, n-1);
            used[i] = false;
        }
        return cnt;
    }

    /**
     * solution
     * DP+Math
     *
     * f(1) = 10
     * f(2) = 9*9
     * f(3) = 9*9*8
     * f(4) = 9*9*8*7
     *
     * f(10) = 9*9*8*7*6*5*4*3*2*1
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        int res = 10;
        int base = 9;
        int remain = 9;
        if (n > 10) n = 10;
        while (n-- > 1) {
            base *= remain;
            remain--;
            res += base;
        }
        return res;
    }
}
