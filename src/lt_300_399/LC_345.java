package lt_300_399;


import java.util.HashSet;
import java.util.Set;

/**
 * [345] Reverse Vowels of a String
 * https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 */
public class LC_345 {
    private static Set<Character> set;
    static {
        set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
    }
    private static boolean isVowel(char c) {
        return set.contains(Character.toLowerCase(c));
    }
    /**
     * a,e,i,o,u
     */
    public String reverseVowels(String s) {
        char[] charArr = s.toCharArray();
        int i = 0, j = charArr.length-1;
        while (i < j) {
            while (i < j && !isVowel(charArr[i])) i++;
            while (i < j && !isVowel(charArr[j])) j--;
            if (i < j) {
                char tmp = charArr[i];
                charArr[i] = charArr[j];
                charArr[j] = tmp;
                i++;
                j--;
            } else {
                break;
            }
        }
        return String.valueOf(charArr);
    }
}
