package lt_700_799;

/**
 * [744] Find Smallest Letter Greater Than Target
 *
 * binary_search
 */
public class LC_744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while(left < right) {
            int mid = left + (right-left)/2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (right == letters.length) {
            return letters[0];
        } else {
            return letters[left];
        }
    }
}
