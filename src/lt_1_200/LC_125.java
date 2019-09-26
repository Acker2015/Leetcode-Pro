package lt_1_200;

public class LC_125 {
    private boolean isAlphaNumeric(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    /**
     * [125] Valid Palindrome
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] chs = s.toCharArray();
        int i = 0, j = chs.length-1;
        while (i < j) {
            while (i < j && !isAlphaNumeric(chs[i])) {
                i++;
            }
            while (i < j && !isAlphaNumeric(chs[j])) {
                j--;
            }
            if (Character.toLowerCase(chs[i]) == Character.toLowerCase(chs[j])) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
