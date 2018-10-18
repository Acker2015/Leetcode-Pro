package lt500_600;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/keyboard-row/submissions/
 * easy
 * @author Acker
 *
 */
public class Leetcode_500 {
	String top = "qwertyuiop";
    String middle = "asdfghjkl";
    String bottom = "zxcvbnm";
    private Set<Character> init(String s) {
        Set<Character> set = new HashSet<>();  
        for (char c: s.toCharArray()) {
            set.add(c);
        }
        return set;
    }
    private boolean judge(Set<Character> set, String word) {
        for (char c: word.toCharArray()) {
            if (!set.contains(c)) {
                return false;
            }
        }
        return true;
    }
    public String[] findWords(String[] words) {
        Set<Character> topSet = init(top);
        Set<Character> middleSet = init(middle);
        Set<Character> bottomSet = init(bottom);
        List<String> ret = new ArrayList<>();
        for (String word: words) {
        		String lowerWord = word.toLowerCase();
        		if (judge(topSet, lowerWord) || judge(middleSet, lowerWord) || judge(bottomSet, lowerWord)) {
        			ret.add(word);
        		}
        }
        return ret.toArray(new String[]{});
        //return Stream.of(words).filter(s -> judge(topSet, s.toLowerCase()) || judge(middleSet, s.toLowerCase()) || judge(bottomSet, s.toLowerCase())).toArray(String[]::new);
        
    }
	public static void main(String[] args) {
		String[] testCases = new String[] {"Hello", "Alaska", "Dad", "Peace"};
		String[] ret = new Leetcode_500().findWords(testCases);
		Stream.of(ret).forEach(System.out::println);
	}

}
