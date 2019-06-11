package lt_400_499;

/**
 * easy
 * [434] Number of Segments in a String
 */
public class LC_434 {
    public int countSegments(String s) {
        int num = 0, i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                i++;
            } else {
                num++;
                int j = i+1;
                while(j < s.length() && s.charAt(j) != ' ') j++;
                i = j;
            }
        }
        return num;
    }
}
