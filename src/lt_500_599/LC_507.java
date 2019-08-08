package lt_500_599;

/**
 * [507] Perfect Number
 */
public class LC_507 {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false;
        int sum = 1;
        for (int i = (int)Math.sqrt(num); i > 1; --i) {
            if (num % i == 0) {
                sum += i==num/i ? i : i+num/i;
            }
        }
        return sum==num;
    }
}
