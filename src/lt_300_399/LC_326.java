package lt_300_399;

/**
 * [326] Power of Three
 */
public class LC_326 {
    public boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        if (n == 1 || n == 3) return true;
        return n%3==0 && isPowerOfThree(n/3);
    }
}
