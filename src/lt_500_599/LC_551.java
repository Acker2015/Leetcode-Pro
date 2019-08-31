package lt_500_599;

/**
 * [551] Student Attendance Record I
 * string
 */
public class LC_551 {
    class Solution {
        /**
         * c1. less than or equals to one A
         * c2. continuous 'L' less than or equals to 2
         */
        public boolean checkRecord(String s) {
            int cntA = 0;
            int maxContinuousL = 0;
            int tmp = 0;
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; ++i) {
                if (chs[i] == 'A') {
                    cntA++;
                } else if (chs[i] == 'L') {
                    tmp++;
                    if (tmp > maxContinuousL) {
                        maxContinuousL = tmp;
                    }
                }
                if (chs[i] != 'L') {
                    tmp = 0;
                }
            }
            return cntA <= 1 && maxContinuousL <= 2;
        }
    }
}
