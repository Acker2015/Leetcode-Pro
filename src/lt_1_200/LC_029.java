package lt_1_200;
/**
 * 我们知道任何一个正数都可以表示成以2的幂为底的一组基的线性组合，即 num = a0 * 2^0 + a1 * 2^1 + … + an * 2^n (a0->an可能为0或者为1)，
	基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。
	然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。
	因为这个方法的迭代次数是按2的幂知道超过结果，所以时间复杂度为O(logn)
	
	考虑几种溢出情况
	1. dividend=-2147483648, divisor=-1
	2. divisor=0 不合法
	3. dividend=-2147483648 在取绝对值的时候如果用int存储也会溢出，这可以使用long型来解决
 * @author Acker
 *
 */
public class LC_029 {
	public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1 || divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) return 0;
        boolean flag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int result = 0, power = 0;
        // b * 2^n
        while ((a >> 1) >= b) {
            b = b << 1;
            power++;
        }
        while (power >= 0 && a >= 0) {
            if (a >= b) {
                result += (1 << power);
                a -= b;
            }
            b = b >> 1;
            power--;
        }
        return flag ? result : -1*result;
    }
	public static void main(String[] args) {
		LC_029 lc_029 = new LC_029();
		System.out.println(lc_029.divide(-2147483648, 1));

	}

}
