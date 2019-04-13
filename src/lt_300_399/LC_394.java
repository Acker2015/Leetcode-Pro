package lt_300_399;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// [394] Decode String
public class LC_394 {
    private char[] chs;
    private Map<Integer, Integer> map = new HashMap<>();
    private String repeat(String s, int r) {
        StringBuilder sbd = new StringBuilder();
        while (r-->0) {
            sbd.append(s);
        }
        return sbd.toString();
    }
    private String decode(int left, int right) {
        if (left > right) return "";
        StringBuilder sbd = new StringBuilder();
        int i = left;
        while (i <= right) {
            if (!Character.isDigit(chs[i])) {
                sbd.append(chs[i++]);
            } else {
                // digit[...], recurse the part in parentheses
                int num = 0;
                while (Character.isDigit(chs[i])) {
                    num = num*10 + (chs[i]-'0');
                    i++;
                }
                // now chs[i] is '['
                String tmp = decode(i+1, map.get(i)-1);
                sbd.append(repeat(tmp, num));
                // update the index j.
                i = map.get(i)+1;
            }
        }
        return sbd.toString();
    }

    /**
     * DFS
     * use stack and map to record the relation of each parentheses
     * use dfs the decode the string
     * 使用map来帮助记录每对[]的起始和结束位置，方便后边搜索递归
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s.length() == 0) return "";
        chs = s.toCharArray();
        map.clear();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < chs.length; ++i) {
            if (chs[i] == '[') stack.push(i);
            if (chs[i] == ']') map.put(stack.pop(), i);
        }
        return decode(0, chs.length-1);
    }

    public static void main(String ...args) {
        LC_394 lc_394 = new LC_394();
        System.out.println(lc_394.decodeString("2[abc]3[cd]ef"));
    }
}
