package lt_400_499;


import java.util.Random;

/**
 * [470] Implement Rand10() Using Rand7()
 *
 */
public class LC_470 {
    public class SolBase {
        private Random random = new Random();

        /**
         * random get 1-7
         * @return
         */
        public int rand7() {
            return random.nextInt(7)+1;
        }
    }
    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */
    class Solution extends SolBase {
        public int rand10() {
            int ans;
            do {
                ans = (rand7()-1) * 7 + rand7() - 1;
            } while (ans >= 40);
            return ans % 10 + 1;
        }
    }
}
