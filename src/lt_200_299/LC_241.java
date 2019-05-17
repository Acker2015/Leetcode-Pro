package lt_200_299;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [241] Different Ways to Add Parentheses
 * 分治
 */
public class LC_241 {
    private boolean singleInteger(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    private int cal(int x, int y, char op) {
        switch(op) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x*y;
            default:
                return 0;
        }
    }

    private Set<Integer> compute(String input) {
        Set<Integer> set = new HashSet<>();
        if (singleInteger(input)) {
            set.add(Integer.parseInt(input));
            return set;
        }

        for (int i = 1; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                Set<Integer> leftSet = compute(input.substring(0, i));
                Set<Integer> rightSet = compute(input.substring(i+1));
                for (Integer l: leftSet) {
                    for (Integer r: rightSet) {
                        set.add(cal(l, r, c));
                    }
                }
            }
        }
        return set;
    }

    /**
     * divide-and-conquer
     * 分治
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        Set<Integer> set = compute(input);
        return new ArrayList<>(set);
    }

    public static void main(String...args) {
        List<Integer> list = new LC_241().diffWaysToCompute("2-1-1");
    }
}
