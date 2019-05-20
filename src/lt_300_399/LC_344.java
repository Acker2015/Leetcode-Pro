package lt_300_399;

/**
 * [344] Reverse String
 */
public class LC_344 {
    /**
     * two pointers
     * @param s
     */
    public void reverseString(char[] s) {
        int i = 0, j = s.length-1;
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
