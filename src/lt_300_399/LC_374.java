package lt_300_399;

/**
 * 374] Guess Number Higher or Lower
 *
 * The guess API is defined in the parent class GuessGame.
 * param num, your guess
 * return -1 if my number is lower, 1 if my number is higher, otherwise return 0
 int guess(int num);
 */
public class LC_374 {
    private class GuessGame {
        int pick;
        protected int guess(int val) {
            if (val == pick) {
                return 0;
            } else if (val < pick) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public class Solution extends GuessGame {
        public int guessNumber(int n) {
            int l = 1, r = n, m;
            while(l<=r) {
                m = l + (r-l)/2;
                int ans = guess(m);
                if (ans == 0) {
                    return m;
                } else if (ans < 0) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return 0;
        }
    }
}
