package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/18
 */
public class LC_069 {
    public int mySqrt(int x) {
        int left = 0, right = x;
        // find first mid, mid*mid>=x, so the result is mid or mid-1
        while (left < right) {
            int mid = left + (right-left)/2;
            if (mid*mid >= x) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left*left==x?left:left-1;
    }

    public static void main(String... args) {
        LC_069 lc_069 = new LC_069();
        System.out.println(lc_069.mySqrt(0));
    }
}
