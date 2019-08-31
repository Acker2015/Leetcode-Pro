package lt_500_599;

public class LC_557 {
    /**
     * @lc app=leetcode id=557 lang=java
     *
     * [557] Reverse Words in a String III
     */
    class Solution {
        private String reverse(String s) {
            return new StringBuilder().append(s).reverse().toString();
        }
        public String reverseWords(String s) {
            String[] splitArr = s.split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < splitArr.length; ++i) {
                builder.append(reverse(splitArr[i]));
                if (i < splitArr.length-1) {
                    builder.append(" ");
                }
            }
            return builder.toString();
        }
    }
}
