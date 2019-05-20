package lt_300_399;

/**
 * [371] Sum of Two Integers(https://leetcode.com/problems/sum-of-two-integers/description/)
 */
public class LC_371 {
    /**
     * a, b
     * 进位可以表示为 (a&b)<<1
     * 结果值可以表示为 a^b
     * 当没有进位为0的时候就能得到最后的结果，否则一直使用结果与进位求和
     */
    public int getSum1(int a, int b) {
        return (a&b) == 0 ? (a ^ b) : getSum((a & b) << 1, a ^ b);
    }

    public int getSum(int a, int b) {
        do {
            int ans = a ^ b;
            int tmp = a & b;
            a = ans;
            b = tmp << 1;
        } while (b != 0);
        return a;
    }
}
