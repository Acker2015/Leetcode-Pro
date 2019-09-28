package lt_1_200;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC_151 {
    /**
     * [151] Reverse Words in a String
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;
        char[] chs = s.toCharArray();
        while (i < chs.length) {
            if (chs[i] == ' ') {
                i++;
            } else {
                int j = i+1;
                while (j < chs.length && chs[j] != ' ') j++;
                list.add(0, s.substring(i, j));
                i = j;
            }
        }
        //Collections.reverse(list);
        return String.join(" ", list);
    }

    public static void main(String ...args) {
        String str="hello world haha";
        LC_151 lc_151 = new LC_151();
        System.out.println(lc_151.reverseWords(str));
    }
}
