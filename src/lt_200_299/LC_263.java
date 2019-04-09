package lt_200_299;

public class LC_263 {
    /**
     * [263] Ugly Number
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        for (int i = 2; i < 6 && num > 1; ++i) {
            while (num > 1 && num % i == 0) {
                num /= i;
            }
        }
        return num == 1;
    }
}
