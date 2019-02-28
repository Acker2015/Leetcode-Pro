package lt_1_200;

import java.util.ArrayList;
import java.util.List;

/**
 * 暴力回溯即可
 * @author Acker
 *
 */
public class LC_017 {
	private static final String[] letters = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	public List<String> letterCombinations(String digits) {
	    char[] dcs = digits.toCharArray();
	    List<String> retList = new ArrayList<>();
	    combination("", 0, digits, retList);
	    return retList;
	}

	public void combination(String prefix, int index, String digits, List<String> retList) {
	    if (index >= digits.length()) {
	        retList.add(prefix);
	        return;
	    }
	    String letter = letters[digits.charAt(index)-'0'];
	    char[] charArr = letter.toCharArray();
	    for (char c: charArr) {
	        combination(prefix+c, index+1, digits, retList);
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LC_017 lc_017 = new LC_017();
		lc_017.letterCombinations("234");
	}

}
