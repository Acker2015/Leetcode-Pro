package lt_300_399;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_392 {
    /**
     * solution1
     * two pointers
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence1(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            char cs = s.charAt(i), ct = s.charAt(j);
            if (cs == ct) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == s.length();
    }

    /**
     * list中找到大于idx的第一个值
     * @param list
     * @param idx
     * @return
     */
    private int binarySearch(List<Integer> list, int idx) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = l + (r-l)/2;
            if (list.get(m) <= idx) {
                l = m+1;
            } else {
                r = m;
            }
        }
        return l>=list.size() ? -1 : list.get(l);
    }

    /**
     * solution2:
     * follow up 如果对于s很多，t固定的情况
     * map + binary_search
     *
     * 将t中的每个字符出现的索引位置转化成一个list(有序)
     *
     * 对于每一个s，只需要遍历s中的每一个字符，同时使用二分查找在上边列表中满足条件的最小位置即可
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            map.computeIfAbsent(t.charAt(i), c->new ArrayList<>()).add(i);
        }
        int lastIdx = -1;
        for (int i = 0; i < s.length(); ++i) {
            List<Integer> list = map.getOrDefault(s.charAt(i), new ArrayList<>());
            lastIdx = binarySearch(list, lastIdx);
            if (lastIdx == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String ...args) {
        LC_392 lc_392 = new LC_392();
        System.out.println(lc_392.isSubsequence("abc", "bbahbgbbdbbc"));
    }
}
