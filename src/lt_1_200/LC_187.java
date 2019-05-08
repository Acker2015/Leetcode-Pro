package lt_1_200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [187] Repeated DNA Sequences
 *
 * hashMap && bit
 */
public class LC_187 {
    private static char[] na = {'A', 'C', 'G', 'T'};
    private static Map<Character, Integer> map = new HashMap<>();
    private static final int MASK = (1 << 20) - 1;
    static {
        for (int i = 0; i < na.length; ++i) {
            map.put(na[i], i);
        }
    }
    private int getSeq(String s, int l, int r) {
        int num = 0;
        for (int i = l; i <= r; ++i) {
            num <<= 2;
            num |= map.get(s.charAt(i));
        }
        return num;
    }
    private int changeStartSeq(int val, char c) {
        return ((val<<2) & MASK) | map.get(c);
    }
    /**
     * A -> 00
     * C -> 01
     * G -> 10
     * T -> 11
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() <= 10) {
            return list;
        }
        HashMap<Integer, Integer> ansMap = new HashMap<>();
        int seq = getSeq(s, 0, 9);
        ansMap.put(seq, 1);
        int i = 1;
        while (i + 10 <= s.length()) {
            seq = changeStartSeq(seq, s.charAt(i+9));
            int appearNum = ansMap.getOrDefault(seq, 0);
            if (appearNum == 1) {
                list.add(s.substring(i, i+10));
            }
            ansMap.put(seq, appearNum+1);
            i++;
        }
        return list;
    }

    public static void main(String ...args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> list = new LC_187().findRepeatedDnaSequences(s);
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
    }
}
