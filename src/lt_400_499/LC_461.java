package lt_400_499;

/**
 * [461] Hamming Distance
 */
public class LC_461 {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int distance = 0;
        while (z != 0) {
            distance++;
            z = z&(z-1);
        }
        return distance;
    }
}
