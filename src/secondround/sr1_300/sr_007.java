package secondround.sr1_300;


public class sr_007 {
    public int reverse(int x) {
        if (x == 0) return x;
        boolean flag = x > 0;
        long a = Math.abs(x);
        long b = 0;
        while (a > 0) {
            b = b*10 + a%10;
            a /= 10;
        }
        b = (flag ? 1 : -1) * b;
        if (b > Integer.MAX_VALUE || b < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)b;
    }
}
