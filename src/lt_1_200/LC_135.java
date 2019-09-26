package lt_1_200;

import java.util.Arrays;

/**
 * [135] Candy
 *
 * greedy
 */
public class LC_135 {

    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len <= 1) return len;
        int[] candies = new int[len];
        // Give each child 1 candy
        Arrays.fill(candies, 1);
        // Scan from left to right, to make sure right higher rated child gets 1 more candy than left lower rated child
        for (int i = 1; i < len; ++i) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1]+1;
            }
        }
        // Scan from right to left, to make sure left higher rated child gets 1 more candy than right lower rated child
        for (int i = len-2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
        }
        int sum = 0;
        for (int i = 0; i < len; ++i) {
            sum += candies[i];
        }
        return sum;
    }
}
