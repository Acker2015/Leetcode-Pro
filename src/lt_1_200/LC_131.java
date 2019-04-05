package lt_1_200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC_131 {
    private char[] chs;
    private int len;

    private boolean palindrome(int l, int r) {
        while (l < r) {
            if (chs[l++] != chs[r--]) {
                return false;
            }
        }
        return true;
    }
    private void backTracking(String s, List<List<String>> retList, List<String> helperList, int index) {
        if (index >= len) {
            retList.add(new LinkedList<>(helperList));
            return;
        }
        for (int i = index; i < len; ++i) {
            if (palindrome(index, i)) {
                helperList.add(s.substring(index, i+1));
                backTracking(s, retList, helperList, i+1);
                helperList.remove(helperList.size()-1);
            }
        }
    }

    /**
     * 暴力回溯 brute backtracking
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        chs = s.toCharArray();
        len = s.length();
        List<List<String>> retList = new LinkedList<>();
        backTracking(s, retList, new ArrayList<>(), 0);
        return retList;
    }
    public static void main(String ...args) {
        LC_131 lc_131 = new LC_131();
        List<List<String>> retList = lc_131.partition("aabaa");
        for (int i =0 ; i < retList.size(); ++i) {
            System.out.println(retList.get(i));
        }
    }
}
