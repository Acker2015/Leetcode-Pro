package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * two solutions:
 * solution1:
 * 		
 * 
 * solution2:
 * 
 * @author Acker
 *
 */
public class LC_022 {
	/**
	 * 	solution1
		My method is DP. First consider how to get the result f(n) from previous result f(0)...f(n-1).
		Actually, the result f(n) will be put an extra () pair to f(n-1). 
		Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.
		Let us consider an example to get clear view:
		f(0): ""
		f(1): "("f(0)")"
		f(2): "("f(0)")"f(1), "("f(1)")"
		f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
		So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"
	
		解法即为 对于fn第一个"("必然出现在最左位置，那么与它匹配的")"的位置多样化，所以这样的前缀(prefix)就有：
		"()fn"
		"(f1)"
		"(f2)"
		"(f3)"
		...
		"(fn-1)"
	*/
	public List<String> generateParenthesis1(int n) {
		List<List<String>> lists = new ArrayList<>();
		lists.add(Arrays.asList(""));
		for (int i = 1; i <= n; ++i) {
			List<String> iList = new ArrayList<>();
			for (int j = 0 ; j < i; ++j) {
				for (String p0: lists.get(j)) {
					for (String p1: lists.get(i - j - 1)) {
						iList.add("("+p0+")"+p1);
					}
				}
			}
			lists.add(iList);
		}
		return lists.get(n);
 	}
	/**
	 * solution2
	 * backtracking
	 * define two variable left and right.
	 * left: the number of '(' need to be added into the combination.
	 * right: the number of ')' need to be added into the combination.
	 * so make sure the right val will add one if you choose to put a '(' into the combination.(left - 1)
	 * thus, we can backtrack the problem based on left and right val.
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
	    List<String> retList = new ArrayList<>();
	    generate(n, 0, "", retList);
	    return retList;  
	}

	private void generate(int left, int right, String prefix, List<String> retList) {
	    if (left <= 0 && right <= 0) {
	    		retList.add(prefix);
	    		return;
	    }
	    if (right > 0) {
	        generate(left, right-1, prefix+')', retList);
	    }
	    if (left > 0) {
	    		generate(left-1, right+1, prefix+'(', retList);
	    }
	}
	public static void main(String[] args) {
		LC_022 lc_022 = new LC_022();
		List<String> retList = lc_022.generateParenthesis(3);
		for(String str: retList) {
			System.out.print(str + " ");
		}
	}

}
