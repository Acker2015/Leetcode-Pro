package lt_200_299;

/**
 * [299] Bulls and Cows
 * hashMap
 */
public class LC_299 {
    /**
     * two-pass
     */
    public String getHint1(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] record = new int[10];
        char[] secretArr = secret.toCharArray();
        char[] guessArr = guess.toCharArray();
        int len = secretArr.length;
        for (int i = 0; i < len; ++i) {
            if (secretArr[i] == guessArr[i]) {
                bulls++;
            } else {
                record[secretArr[i]-'0']++;
            }
        }
        // 为什么不在第二遍遍历的时候将bulls一起解决掉
        // 因为方式cows占用bulls导致混乱，如0123-0223, guess中的第一个二既不是bulls也不是cows
        for (int i = 0; i < guess.length(); ++i) {
            int loc = guessArr[i]-'0';
            if (secretArr[i] != guessArr[i] && record[loc] > 0) {
                cows++;
                record[loc]--;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(bulls).append('A');
        builder.append(cows).append('B');
        return builder.toString();
    }

    /**
     * one-pass
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        // secret中对应位不同的char，使其自增
        // guess中对应位置不同的char，使其自减
        int[] record = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            int s = secret.charAt(i)-'0';
            int g = guess.charAt(i)-'0';
            if (s == g) {
                bulls++;
            } else {
                if (record[s] < 0) cows++;
                if (record[g] > 0) cows++;
                record[s]++;
                record[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
