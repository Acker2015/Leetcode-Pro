package lt_400_499;

/**
 * [476] Number Complement
 */
public class LC_476 {
    private int findMask(int num) {
        int mask = 0;
        while (num != 0) {
            mask = (mask<<1)|1;
            num >>= 1;
        }
        return mask;
    }

    public int findComplement(int num) {
        return num ^ findMask(num);
    }
}
